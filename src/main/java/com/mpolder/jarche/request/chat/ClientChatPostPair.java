package com.mpolder.jarche.request.chat;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.ChatRequestHandler;
import com.mpolder.jarche.request.handler.IEventHandler;

public class ClientChatPostPair implements IRequestPair {
    @Override
    public Class<? extends IEventHandler> handler() {
        return ChatRequestHandler.class;
    }

    @Override
    public Class<? extends IRequest> request() {
        return ClientChatPostRequest.class;
    }

    @Override
    public Class<? extends IConfirmation> confirmation() {
        return ClientChatPostConfirmation.class;
    }
}
