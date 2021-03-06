package com.mpolder.jarche.request.helloworld;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.HelloWorldRequestHandler;
import com.mpolder.jarche.request.handler.IEventHandler;

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
