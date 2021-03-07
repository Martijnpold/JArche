package com.mpolder.jarche.base;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;

public class BasicWebSocketClient extends WebSocketClient {
    private final HashMap<ConnectionEvent, DataListener> listeners;

    public BasicWebSocketClient(URI address) {
        super(address);
        this.listeners = new HashMap<>();
    }

    public <T> void addEventListener(ConnectionEvent event, DataListener onData) {
        listeners.put(event, onData);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        DataListener c = listeners.get(ConnectionEvent.CONNECT);
        if (c != null) c.onData(getConnection(), serverHandshake);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        DataListener c = listeners.get(ConnectionEvent.DISCONNECT);
        if (c != null) c.onData(getConnection(), s);
    }

    @Override
    public void onMessage(String s) {
        DataListener c = listeners.get(ConnectionEvent.MESSAGE);
        if (c != null) c.onData(getConnection(), s);
    }

    @Override
    public void onError(Exception e) {
        DataListener c = listeners.get(ConnectionEvent.ERROR);
        if (c != null) c.onData(getConnection(), e);
    }
}
