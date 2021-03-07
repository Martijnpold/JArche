package com.mpolder.jarche.example.request;

import com.mpolder.jarche.example.handler.HelloWorldRequestHandler;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.interfaces.IEventHandler;

public class HelloWorldPair implements IRequestPair {
    @Override
    public Class<? extends IEventHandler> handler() {
        return HelloWorldRequestHandler.class;
    }

    @Override
    public Class<? extends IRequest> request() {
        return HelloWorldRequest.class;
    }

    @Override
    public Class<? extends IConfirmation> confirmation() {
        return HelloWorldConfirmation.class;
    }
}
