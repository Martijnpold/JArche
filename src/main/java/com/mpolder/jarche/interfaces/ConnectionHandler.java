package com.mpolder.jarche.interfaces;

import org.java_websocket.WebSocket;

public abstract class ConnectionHandler implements IEventHandler {
    public abstract void onConnect(WebSocket client);

    public abstract void onDisconnect(WebSocket client);
}
