package com.mpolder.jarche.base;

import com.mpolder.jarche.AuctionServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class BasicWebSocketServer extends WebSocketServer {
    private AuctionServer auctionServer;
    private HashMap<ServerEvent, DataListener> listeners;

    public BasicWebSocketServer(AuctionServer auctionServer, InetSocketAddress address) {
        super(address);
        this.auctionServer = auctionServer;
        this.listeners = new HashMap<>();
    }

    public <T> void addEventListener(ServerEvent event, DataListener onData) {
        listeners.put(event, onData);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        DataListener c = listeners.get(ServerEvent.CONNECT);
        if (c != null) c.onData(auctionServer, webSocket, clientHandshake);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        DataListener c = listeners.get(ServerEvent.DISCONNECT);
        if (c != null) c.onData(auctionServer, webSocket, s);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        DataListener c = listeners.get(ServerEvent.MESSAGE);
        if (c != null) c.onData(auctionServer, webSocket, s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        DataListener c = listeners.get(ServerEvent.ERROR);
        if (c != null) c.onData(auctionServer, webSocket, e);
    }

    @Override
    public void onStart() {

    }
}
