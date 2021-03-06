package com.mpolder.jarche.request.handler;

import com.mpolder.jarche.request.group.*;
import org.java_websocket.WebSocket;

public abstract class GroupRequestHandler implements IEventHandler {
    public abstract ClientGroupCreateConfirmation handle(WebSocket socket, ClientGroupCreateRequest req);

    public abstract ClientGroupDeleteConfirmation handle(WebSocket socket, ClientGroupDeleteRequest req);

    public abstract ClientGroupJoinConfirmation handle(WebSocket socket, ClientGroupJoinRequest req);

    public abstract ClientGroupLeaveConfirmation handle(WebSocket socket, ClientGroupLeaveRequest req);

    public abstract ClientGroupListConfirmation handle(WebSocket socket, ClientGroupListRequest req);
}
