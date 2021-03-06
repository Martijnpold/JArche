package com.mpolder.jarche.request.handler;

import com.mpolder.jarche.JArcheServer;
import org.java_websocket.WebSocket;

public abstract class ServerConnectionHandler implements IEventHandler {
    public abstract void onConnect(JArcheServer server, WebSocket client);

    public abstract void onDisconnect(JArcheServer server, WebSocket client);
}
