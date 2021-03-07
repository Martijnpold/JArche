package com.mpolder.jarche.request.handler;

import org.java_websocket.WebSocket;

public abstract class ServerConnectionHandler implements IEventHandler {
    public abstract void onConnect(WebSocket client);

    public abstract void onDisconnect(WebSocket client);
}
