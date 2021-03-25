package com.mpolder.jarche;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.mpolder.jarche.interfaces.*;
import com.mpolder.jarche.request.*;
import lombok.SneakyThrows;
import org.java_websocket.WebSocket;

import java.util.UUID;

public class JArcheBackend {
    private final RequestCache requestCache;
    private final HandlerCache handlerCache;

    //Object Mapping
    private final PairCache pairCache;
    private final ObjectMapper objectMapper;

    public JArcheBackend() {
        this.requestCache = new RequestCache();
        this.pairCache = new PairCache();
        this.handlerCache = new HandlerCache(pairCache);
        this.objectMapper = JsonMapper.builder()
                .addModule(new ParameterNamesModule())
                .addModule(new Jdk8Module())
                .addModule(new JavaTimeModule())
                .build();
    }

    @SneakyThrows
    public ResponseHandler send(WebSocket session, IRequest request) {
        if (!request.validate()) {
            System.out.println("Outgoing request has an invalid structure!" + request.getClass().getSimpleName());
            return null;
        }

        UUID id = UUID.randomUUID();
        ResponseHandler handler = new ResponseHandler(id);

        if (session != null && !session.isClosed()) {
            FullRequest toSend = new FullRequest(id, request.getClass().getSimpleName(), objectMapper.valueToTree(request));
            session.send(objectMapper.writeValueAsString(toSend));
            requestCache.cache(new SentRequest(id, request, handler));
        } else {
            throw new RuntimeException("Socket is not connected.");
        }
        return handler;
    }

    public void handleData(WebSocket session, String data) {
        try {
            JsonNode tree = objectMapper.readTree(data);
            if (tree.has("request")) handleRequest(session, tree);
            if (tree.has("confirmation")) handleConfirmation(tree);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void handleRequest(WebSocket session, JsonNode data) {
        FullRequest fullRequest = objectMapper.treeToValue(data, FullRequest.class);
        IRequestPair pair = pairCache.get(fullRequest.getType());
        if (pair != null) {
            IRequest req = objectMapper.treeToValue(fullRequest.getRequest(), pair.request());
            if (!req.validate()) {
                System.out.println("Incoming request has an invalid structure!");
                return;
            }
            IConfirmation conf = handlerCache.execute(session, req);
            send(session, conf, fullRequest.getId());
        } else {
            System.out.println("Received invalid request type: " + fullRequest.getType());
        }
    }

    @SneakyThrows
    public void send(WebSocket session, IConfirmation confirmation, UUID id) {
        if (confirmation.validate()) {
            if (session != null) {
                FullConfirmation fullConfirmation = new FullConfirmation(id, objectMapper.valueToTree(confirmation));
                session.send(objectMapper.writeValueAsString(fullConfirmation));
            }
        }
    }

    @SneakyThrows
    public void handleConfirmation(JsonNode data) {
        FullConfirmation fullConfirmation = objectMapper.treeToValue(data, FullConfirmation.class);
        SentRequest req = requestCache.decache(fullConfirmation.getId());
        if (req != null) {
            IRequestPair pair = pairCache.get(req.getSource());
            IConfirmation conf = objectMapper.treeToValue(fullConfirmation.getConfirmation(), pair.confirmation());
            if (!conf.validate()) {
                System.out.println("Incoming confirmation has an invalid structure!");
                return;
            }
            req.getHandler().resolve(conf);
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
