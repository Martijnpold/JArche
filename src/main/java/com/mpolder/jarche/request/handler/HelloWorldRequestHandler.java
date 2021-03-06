package com.mpolder.jarche.request.handler;

import com.mpolder.jarche.request.helloworld.HelloWorldConfirmation;
import com.mpolder.jarche.request.helloworld.HelloWorldRequest;
import org.java_websocket.WebSocket;

public abstract class HelloWorldRequestHandler implements IEventHandler {
    public abstract HelloWorldConfirmation handle(WebSocket socket, HelloWorldRequest req);
}
