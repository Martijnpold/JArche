package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.GroupRequestHandler;
import com.mpolder.jarche.request.handler.IEventHandler;

public class ClientGroupDeletePair implements IRequestPair {

    @Override
    public Class<? extends IEventHandler> handler() {
        return GroupRequestHandler.class;
    }

    @Override
    public Class<? extends IRequest> request() {
        return ClientGroupDeleteRequest.class;
    }

    @Override
    public Class<? extends IConfirmation> confirmation() {
        return ClientGroupDeleteConfirmation.class;
    }
}