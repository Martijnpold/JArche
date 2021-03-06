package com.mpolder.jarche.request.auction;

import com.mpolder.jarche.interfaces.IConfirmation;

public class ServerEndRoundConfirmation implements IConfirmation {
    @Override
    public boolean validate() {
        return true;
    }
}
