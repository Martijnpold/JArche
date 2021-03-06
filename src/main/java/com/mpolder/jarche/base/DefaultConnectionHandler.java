package com.mpolder.jarche.base;

import com.mpolder.jarche.AuctionServer;
import com.mpolder.jarche.request.handler.ServerConnectionHandler;
import com.mpolder.jarche.request.helloworld.HelloWorldRequest;
import org.java_websocket.WebSocket;

public class DefaultConnectionHandler extends ServerConnectionHandler {
    @Override
    public void onConnect(AuctionServer server, WebSocket client) {
        System.out.println("Connected: " + client.getRemoteSocketAddress());
        new Thread(() -> {
            try {
                Thread.sleep(500);
                server.send(client, new HelloWorldRequest("some test message"));
                System.out.println("Request sent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void onDisconnect(AuctionServer server, WebSocket client) {
        System.out.println("Disconnected: " + client.getRemoteSocketAddress());
    }
}
