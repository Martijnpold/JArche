package com.mpolder.jarche.request.chat;

import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.model.User;

public class ServerChatPostRequest implements IRequest {
    private User user;
    private String message;
    private long timestamp;

    public ServerChatPostRequest() {
    }

    public ServerChatPostRequest(User user, String message, long timestamp) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean validate() {
        boolean valid = this.user != null && this.message != null && this.timestamp != 0;
        valid &= !this.message.equals("");
        return valid;
    }
}
