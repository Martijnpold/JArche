package com.mpolder.jarche;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mpolder.jarche.interfaces.FeatureProfile;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.*;
import com.mpolder.jarche.request.handler.IEventHandler;
import org.java_websocket.WebSocket;

import java.io.IOException;
import java.util.UUID;

public class JArcheBackend {
    private final RequestCache requestCache;
    private final HandlerCache handlerCache;

    //Object Mapping
    private final PairCache pairCache;
    private final ObjectMapper objectMapper;
    private final JsonParser jsonParser;
    private final Gson gson;

    public JArcheBackend() {
        this.requestCache = new RequestCache();
        this.pairCache = new PairCache();
        this.handlerCache = new HandlerCache(pairCache);
        this.objectMapper = new ObjectMapper();
        this.jsonParser = new JsonParser();
        this.gson = new Gson();
    }

    public ResponseHandler send(WebSocket session, IRequest request) {
        if (!request.validate()) {
            System.out.println("Outgoing request has an invalid structure!" + request.getClass().getSimpleName());
            return null;
        }
        UUID id = UUID.randomUUID();
        ResponseHandler handler = new ResponseHandler(id);
        requestCache.cache(new SentRequest(id, request, handler));

        JsonObject json = new JsonObject();
        json.addProperty("type", request.getClass().getSimpleName());
        json.addProperty("id", id.toString());
        json.add("request", jsonParser.parse(gson.toJson(request)));

        if (session != null) {
            if (!session.isClosed()) {
                session.send(json.toString());
            } else {
                handlerCache.getConnectionHandler().onDisconnect(session);
            }
        }
        return handler;
    }

    public IConfirmation handleRequest(WebSocket session, String data) {
        try {
            JsonObject json = jsonParser.parse(data).getAsJsonObject();
            if (json.has("request")) {
                UUID id = UUID.fromString(json.get("id").getAsString());
                String type = json.get("type").getAsString();
                IRequestPair pair = pairCache.get(type);
                if (pair != null) {
                    IRequest req = objectMapper.readValue(json.get("request").getAsJsonObject().toString(), pair.request());
                    if (!req.validate()) {
                        System.out.println("Incoming request has an invalid structure!");
                        return null;
                    }
                    IConfirmation conf = handlerCache.execute(session, req);
                    send(session, conf, id);
                    return conf;
                } else {
                    System.out.println("Received invalid request type: " + type);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject send(WebSocket session, IConfirmation confirmation, UUID id) {
        if (confirmation.validate()) {
            JsonObject json = new JsonObject();
            json.addProperty("id", id.toString());
            json.add("confirmation", jsonParser.parse(gson.toJson(confirmation)));

            if (session != null) session.send(json.toString());
            return json;
        }
        System.out.println("Outgoing confirmation has an invalid structure!");
        return null;
    }

    public void handleConfirmation(String data) {
        try {
            JsonObject json = jsonParser.parse(data).getAsJsonObject();
            UUID uuid = UUID.fromString(json.get("id").getAsString());
            SentRequest req = requestCache.decache(uuid);
            if (req != null) {
                IRequestPair pair = pairCache.get(req.getSource());
                IConfirmation conf = objectMapper.readValue(json.get("request").getAsJsonObject().toString(), pair.confirmation());
                if (!conf.validate()) {
                    System.out.println("Incoming confirmation has an invalid structure!");
                    return;
                }
                req.getHandler().resolve(conf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConnect(WebSocket socket) {
        handlerCache.getConnectionHandler().onConnect(socket);
    }

    public void handleDisconnect(WebSocket socket) {
        handlerCache.getConnectionHandler().onDisconnect(socket);
    }

    /**
     * Register a feature profile
     *
     * @param profile profile to register
     */
    public void registerProfile(FeatureProfile profile) {
        handlerCache.registerProfile(profile);
    }

    /**
     * Register a request handler
     *
     * @param handler handler to register
     */
    public void registerHandler(IEventHandler handler) {
        handlerCache.registerHandler(handler);
    }
}
