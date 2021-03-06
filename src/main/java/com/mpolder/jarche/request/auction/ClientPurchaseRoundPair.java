package com.mpolder.jarche.request.auction;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.handler.AuctionRequestHandler;
import com.mpolder.jarche.request.handler.IEventHandler;

public class ClientPurchaseRoundPair implements IRequestPair {
    @Override
    public Class<? extends IEventHandler> handler() {
        return AuctionRequestHandler.class;
    }

    @Override
    public Class<? extends IRequest> request() {
        return ClientPurchaseRoundRequest.class;
    }

    @Override
    public Class<? extends IConfirmation> confirmation() {
        return ClientPurchaseRoundConfirmation.class;
    }
}
