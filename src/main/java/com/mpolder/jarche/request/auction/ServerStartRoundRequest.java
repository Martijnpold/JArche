package com.mpolder.jarche.request.auction;

import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.model.Flower;

public class ServerStartRoundRequest implements IRequest {
    private Flower flower;

    public ServerStartRoundRequest() {
    }

    public ServerStartRoundRequest(Flower flower) {
        this.flower = flower;
    }

    public Flower getFlower() {
        return flower;
    }

    @Override
    public boolean validate() {
        boolean valid = this.flower != null;
        return valid;
    }
}
