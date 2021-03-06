package com.mpolder.jarche.request.user;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.IEventHandler;
import com.mpolder.jarche.request.handler.UserRequestHandler;

public class ClientReceiveCodePair implements IRequestPair {
    @Override
    public Class<? extends IEventHandler> handler() {
        return UserRequestHandler.class;
    }

    @Override
    public Class<? extends IRequest> request() {
        return ClientReceiveCodeRequest.class;
    }

    @Override
    public Class<? extends IConfirmation> confirmation() {
        return ClientReceiveCodeConfirmation.class;
    }
}
