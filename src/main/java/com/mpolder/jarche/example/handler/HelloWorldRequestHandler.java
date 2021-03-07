package com.mpolder.jarche.example.handler;

import com.mpolder.jarche.example.request.HelloWorldConfirmation;
import com.mpolder.jarche.example.request.HelloWorldRequest;
import com.mpolder.jarche.interfaces.IEventHandler;
import org.java_websocket.WebSocket;

public abstract class HelloWorldRequestHandler implements IEventHandler {
    public abstract HelloWorldConfirmation handle(WebSocket socket, HelloWorldRequest req);
}
