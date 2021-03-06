package com.mpolder.jarche.request.chat;

import com.mpolder.jarche.interfaces.IRequest;

public class ClientChatPostRequest implements IRequest {
    private String message;

    public ClientChatPostRequest() {
    }

    public ClientChatPostRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean validate() {
        boolean valid = this.message != null && !this.message.equals("");
        return valid;
    }
}
