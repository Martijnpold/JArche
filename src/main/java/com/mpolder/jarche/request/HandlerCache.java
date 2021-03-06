package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.IEventHandler;
import com.mpolder.jarche.request.handler.ServerConnectionHandler;
import org.java_websocket.WebSocket;

import java.lang.reflect.Method;
import java.util.HashMap;

public class HandlerCache {
    private HashMap<Class<? extends IRequest>, IEventHandler> handlers;
    private ServerConnectionHandler connectionHandler;
    private PairCache pairCache;

    public HandlerCache(PairCache pairCache) {
        handlers = new HashMap<>();
        this.pairCache = pairCache;
    }

    public void registerHandler(IEventHandler handler) {
        if (handler instanceof ServerConnectionHandler) {
            this.connectionHandler = (ServerConnectionHandler) handler;
        }
        for (IRequestPair pair : pairCache.getAll()) {
            if (pair.handler() == null) continue;
            if (pair.handler().isAssignableFrom(handler.getClass())) {
                handlers.put(pair.request(), handler);
            }
        }
    }

    public IConfirmation execute(WebSocket webSocket, IRequest req) {
        try {
            IEventHandler handler = handlers.get(req.getClass());
            if (handler != null) {
                Method handleMethod = handler.getClass().getMethod("handle", WebSocket.class, req.getClass());
                handleMethod.setAccessible(true);
                return (IConfirmation) handleMethod.invoke(handler, webSocket, req);
            } else {
                System.out.println("Could not find handler for request type " + req.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ServerConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }
}
