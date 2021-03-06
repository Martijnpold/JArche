package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.GroupRequestHandler;
import com.mpolder.jarche.request.handler.IEventHandler;

public class ClientGroupJoinPair implements IRequestPair {
    @Override
    public Class<? extends IEventHandler> handler() {
        return GroupRequestHandler.class;
    }

    @Override
    public Class<? extends IRequest> request() {
        return ClientGroupJoinRequest.class;
    }

    @Override
    public Class<? extends IConfirmation> confirmation() {
        return ClientGroupJoinConfirmation.class;
    }
}
