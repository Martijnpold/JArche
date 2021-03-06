package com.mpolder.jarche.request.auction;

import com.mpolder.jarche.interfaces.IRequest;

public class ClientPurchaseRoundRequest implements IRequest {
    private double price;

    public ClientPurchaseRoundRequest() {
    }

    public ClientPurchaseRoundRequest(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean validate() {
        boolean valid = this.price != 0;
        return valid;
    }
}
