package com.mpolder.jarche.request.handler;

import com.mpolder.jarche.request.user.*;
import org.java_websocket.WebSocket;

public abstract class UserRequestHandler implements IEventHandler {
    public abstract ClientUpdateUserConfirmation handle(WebSocket socket, ClientUpdateUserRequest req);

    public abstract ClientCoupleUserConfirmation handle(WebSocket socket, ClientCoupleUserRequest req);

    public abstract ClientReceiveCodeConfirmation handle(WebSocket socket, ClientReceiveCodeRequest req);
    
    public abstract ClientResumeSessionConfirmation handle(WebSocket socket, ClientResumeSessionRequest req);
}
