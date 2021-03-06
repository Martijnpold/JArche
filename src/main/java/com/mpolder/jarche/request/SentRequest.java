package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.IRequest;
import lombok.Getter;

import java.util.UUID;

public class SentRequest {
    @Getter
    private final UUID id;
    @Getter
    private final IRequest source;
    @Getter
    private final ResponseHandler handler;

    public SentRequest(UUID id, IRequest source, ResponseHandler handler) {
        this.id = id;
        this.source = source;
        this.handler = handler;
    }
}
