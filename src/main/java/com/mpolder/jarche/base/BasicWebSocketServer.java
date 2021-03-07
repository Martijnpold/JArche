package com.mpolder.jarche.base;

import com.mpolder.jarche.JArcheServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class BasicWebSocketServer extends WebSocketServer {
    private final HashMap<ConnectionEvent, DataListener> listeners;

    public BasicWebSocketServer(InetSocketAddress address) {
        super(address);
        this.listeners = new HashMap<>();
    }

    public <T> void addEventListener(ConnectionEvent event, DataListener onData) {
        listeners.put(event, onData);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        DataListener c = listeners.get(ConnectionEvent.CONNECT);
        if (c != null) c.onData(webSocket, clientHandshake);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        DataListener c = listeners.get(ConnectionEvent.DISCONNECT);
        if (c != null) c.onData(webSocket, s);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        DataListener c = listeners.get(ConnectionEvent.MESSAGE);
        if (c != null) c.onData(webSocket, s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        DataListener c = listeners.get(ConnectionEvent.ERROR);
        if (c != null) c.onData(webSocket, e);
    }

    @Override
    public void onStart() {

    }
}
