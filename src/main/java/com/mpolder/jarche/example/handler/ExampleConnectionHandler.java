package com.mpolder.jarche.example.handler;

import com.mpolder.jarche.interfaces.ConnectionHandler;
import org.java_websocket.WebSocket;

public class ExampleConnectionHandler extends ConnectionHandler {
    @Override
    public void onConnect(WebSocket client) {
        System.out.println("Client connected");
    }

    @Override
    public void onDisconnect(WebSocket client) {
        System.out.println("Client disconnected");
    }
}
