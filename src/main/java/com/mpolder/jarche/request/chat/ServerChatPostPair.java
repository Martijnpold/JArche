package com.mpolder.jarche.request.chat;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.IEventHandler;

public class ServerChatPostPair implements IRequestPair {
    @Override
    public Class<? extends IEventHandler> handler() {
        return null;
    }

    @Override
    public Class<? extends IRequest> request() {
        return ServerChatPostRequest.class;
    }

    @Override
    public Class<? extends IConfirmation> confirmation() {
        return ServerChatPostConfirmation.class;
    }
}
