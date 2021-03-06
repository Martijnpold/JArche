package com.mpolder.jarche.request.auction;

import com.google.gson.annotations.SerializedName;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.model.Flower;

public class ServerEndRoundRequest implements IRequest {
    private Flower flower;
    @SerializedName("was_buyer")
    private boolean wasBuyer;
    @SerializedName("buy_price")
    private double buyPrice;

    public ServerEndRoundRequest() {
    }

    public ServerEndRoundRequest(Flower flower, boolean wasBuyer, double buyPrice) {
        this.flower = flower;
        this.wasBuyer = wasBuyer;
        this.buyPrice = buyPrice;
    }

    public Flower getFlower() {
        return flower;
    }

    public boolean isWasBuyer() {
        return wasBuyer;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    @Override
    public boolean validate() {
        boolean valid = this.flower != null;
        return valid;
    }
}
