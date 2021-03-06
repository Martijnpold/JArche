package com.mpolder.jarche.request.handler;

import com.mpolder.jarche.request.auction.ClientPurchaseRoundConfirmation;
import com.mpolder.jarche.request.auction.ClientPurchaseRoundRequest;
import org.java_websocket.WebSocket;

public abstract class AuctionRequestHandler implements IEventHandler {
    public abstract ClientPurchaseRoundConfirmation handle(WebSocket socket, ClientPurchaseRoundRequest req);
}
