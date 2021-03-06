package com.mpolder.jarche.request.auction;

import com.mpolder.jarche.interfaces.IConfirmation;

public class ServerStartRoundConfirmation implements IConfirmation {
    @Override
    public boolean validate() {
        return true;
    }
}
