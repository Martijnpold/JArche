package com.mpolder.jarche;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mpolder.jarche.base.BasicWebSocketServer;
import com.mpolder.jarche.base.DefaultConnectionHandler;
import com.mpolder.jarche.base.ServerEvent;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.*;
import com.mpolder.jarche.request.handler.IEventHandler;
import org.java_websocket.WebSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuctionServer {
    private BasicWebSocketServer wsServer;
    private RequestCache requestCache;
    private HandlerCache handlerCache;

    //Object Mapping
    private PairCache pairCache;
    private ObjectMapper objectMapper;
    private JsonParser jsonParser;
    private Gson gson;

    public AuctionServer() {
        this.requestCache = new RequestCache();
        this.pairCache = new PairCache();
        this.handlerCache = new HandlerCache(pairCache);
        this.objectMapper = new ObjectMapper();
        this.jsonParser = new JsonParser();
        this.gson = new Gson();
        this.wsServer = new BasicWebSocketServer(this, new InetSocketAddress(27015));
        registerHandler(new DefaultConnectionHandler());
        registerListeners();
    }

    /**
     * Start the auction server instance
     */
    public void start() {
        wsServer.start();
    }

    /**
     * Initialize the listeners
     */
    private void registerListeners() {
        wsServer.addEventListener(ServerEvent.MESSAGE, ((server, socket, data) -> {
            receiveRequest(socket, (String) data);
            receiveConfirmation((String) data);
        }));
        wsServer.addEventListener(ServerEvent.CONNECT, (server, socket, data) -> {
            handlerCache.getConnectionHandler().onConnect(this, socket);
        });
        wsServer.addEventListener(ServerEvent.DISCONNECT, (server, socket, data) -> {
            handlerCache.getConnectionHandler().onDisconnect(this, socket);
        });
    }

    public IConfirmation receiveRequest(WebSocket session, String data) {
        try {
            JsonObject json = jsonParser.parse(data).getAsJsonObject();
            if(json.has("request")) {
                UUID id = UUID.fromString(json.get("id").getAsString());
                String type = json.get("type").getAsString();
                IRequestPair pair = pairCache.get(type);
                if(pair != null) {
                    IRequest req = objectMapper.readValue(json.get("request").getAsJsonObject().toString(), pair.request());
                    if (!req.validate()) { System.out.println("Incoming request has an invalid structure!"); return null; }
                    IConfirmation conf = handlerCache.execute(session, req);
                    sendConf(session, conf, id);
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

    public void stop() {
        try {
            wsServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JsonObject sendConf(WebSocket session, IConfirmation confirmation, UUID id) {
        if (!confirmation.validate()) { System.out.println("Outgoing confirmation has an invalid structure!"); return null; }
        JsonObject json = new JsonObject();
        json.addProperty("id", id.toString());
        json.add("confirmation", jsonParser.parse(gson.toJson(confirmation)));

        if (session != null) session.send(json.toString());
        return json;
    }

    /**
     * Send a request to a single session
     *
     * @param session UUID of the session to emit to
     * @param request Request to transmit
     * @return ResponseHandler being executed on confirmation
     */
    public ResponseHandler send(WebSocket session, IRequest request) {
        if (!request.validate()) { System.out.println("Outgoing request has an invalid structure!" + request.getClass().getSimpleName()); return null; }
        UUID id = UUID.randomUUID();
        ResponseHandler handler = new ResponseHandler(id);
        requestCache.cache(new SentRequest(id, request, handler));

        JsonObject json = new JsonObject();
        json.addProperty("type", request.getClass().getSimpleName());
        json.addProperty("id", id.toString());
        json.add("request", jsonParser.parse(gson.toJson(request)));

        if (session != null) {
            if(!session.isClosed()) {
                session.send(json.toString());
            } else {
                handlerCache.getConnectionHandler().onDisconnect(this, session);
            }
        }
        return handler;
    }

    /**
     * Send a request to all connected clients
     *
     * @param request Request to transmit
     * @return ResponseHandlers being executed on confirmation
     */
    public List<ResponseHandler> sendAll(IRequest request) {
        List<ResponseHandler> handlers = new ArrayList<>();
        for (WebSocket client : wsServer.getConnections()) {
            ResponseHandler handler = send(client, request);
            handlers.add(handler);
        }
        return handlers;
    }

    /**
     * Receive a plain json confirmation string
     *
     * @param data Plain json data
     */
    public void receiveConfirmation(String data) {
        try {
            JsonObject json = jsonParser.parse(data).getAsJsonObject();
            UUID uuid = UUID.fromString(json.get("id").getAsString());
            SentRequest req = requestCache.decache(uuid);
            if (req != null) {
                IRequestPair pair = pairCache.get(req.getSource());
                IConfirmation conf = objectMapper.readValue(json.get("request").getAsJsonObject().toString(), pair.confirmation());
                if (!conf.validate()) { System.out.println("Incoming confirmation has an invalid structure!"); return; }
                req.getHandler().resolve(conf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
