package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.IRequest;

import java.util.UUID;

public class SentRequest {
    private UUID id;
    private IRequest source;
    private ResponseHandler handler;

    public SentRequest(UUID id, IRequest source, ResponseHandler handler) {
        this.id = id;
        this.source = source;
        this.handler = handler;
    }

    public UUID getId() {
        return id;
    }

    public IRequest getSource() {
        return source;
    }

    public ResponseHandler getHandler() {
        return handler;
    }
}
