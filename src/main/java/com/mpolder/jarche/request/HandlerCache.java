package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.FeatureProfile;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.interfaces.IEventHandler;
import com.mpolder.jarche.interfaces.ConnectionHandler;
import lombok.Getter;
import org.java_websocket.WebSocket;

import java.lang.reflect.Method;
import java.util.HashMap;

public class HandlerCache {
    private final HashMap<Class<? extends IRequest>, IEventHandler> handlers;
    private final PairCache pairCache;

    @Getter
    private ConnectionHandler connectionHandler;

    public HandlerCache(PairCache pairCache) {
        handlers = new HashMap<>();
        this.pairCache = pairCache;
    }

    public void registerProfile(FeatureProfile profile) {
        profile.getRequests().forEach(pairCache::register);
    }

    public void registerHandler(IEventHandler handler) {
        if (handler instanceof ConnectionHandler) {
            this.connectionHandler = (ConnectionHandler) handler;
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
}
