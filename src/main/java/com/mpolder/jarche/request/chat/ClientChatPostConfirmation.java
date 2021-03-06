package com.mpolder.jarche.request.chat;

import com.mpolder.jarche.interfaces.IConfirmation;

public class ClientChatPostConfirmation implements IConfirmation {
    @Override
    public boolean validate() {
        return true;
    }
}
