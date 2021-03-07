package com.mpolder.jarche.example;

import com.mpolder.jarche.JArcheServer;
import com.mpolder.jarche.example.handler.ExampleConnectionHandler;
import com.mpolder.jarche.example.handler.HelloWorldRequestHandler;
import com.mpolder.jarche.example.profile.HelloWorldProfile;
import com.mpolder.jarche.example.request.HelloWorldConfirmation;
import com.mpolder.jarche.example.request.HelloWorldRequest;
import org.java_websocket.WebSocket;

public class ExampleServer {
    public static void main(String[] args) {
        JArcheServer server = new JArcheServer(27015);

        server.registerProfile(new HelloWorldProfile());
        server.registerHandler(new ExampleConnectionHandler());
        server.registerHandler(new HelloWorldRequestHandler() {
            @Override
            public HelloWorldConfirmation handle(WebSocket socket, HelloWorldRequest req) {
                System.out.println("Received " + req.getMessage());
                return new HelloWorldConfirmation(req.getMessage() + " World!");
            }
        });

        server.start();
    }
}
