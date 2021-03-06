package com.mpolder.jarche.request.handler;

import com.mpolder.jarche.request.chat.ClientChatPostConfirmation;
import com.mpolder.jarche.request.chat.ClientChatPostRequest;
import org.java_websocket.WebSocket;

public abstract class ChatRequestHandler implements IEventHandler {
    public abstract ClientChatPostConfirmation handle(WebSocket socket, ClientChatPostRequest req);
}
