package com.mpolder.jarche.request.handler;

import com.mpolder.jarche.AuctionServer;
import org.java_websocket.WebSocket;

public abstract class ServerConnectionHandler implements IEventHandler {
    public abstract void onConnect(AuctionServer server, WebSocket client);

    public abstract void onDisconnect(AuctionServer server, WebSocket client);
}
