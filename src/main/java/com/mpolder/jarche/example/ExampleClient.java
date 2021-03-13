package com.mpolder.jarche.example;

import com.mpolder.jarche.JArcheClient;
import com.mpolder.jarche.example.handler.ExampleConnectionHandler;
import com.mpolder.jarche.example.request.HelloWorldConfirmation;
import com.mpolder.jarche.example.request.HelloWorldRequest;

import java.net.URISyntaxException;

public class ExampleClient {
    public static void main(String[] args) throws URISyntaxException {
        JArcheClient client = new JArcheClient("ws://127.0.0.1", 27015);
        client.registerHandler(new ExampleConnectionHandler());
        client.connect();

        System.out.println("Sending Helloworld");
        client.send(new HelloWorldRequest("Hello")).onResponse(x -> {
            System.out.println("Received " + ((HelloWorldConfirmation) x).getMessage());
        });
    }
}
