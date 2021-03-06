package com.mpolder.jarche.request.user;

import com.mpolder.jarche.interfaces.IRequest;

public class ClientUpdateUserRequest implements IRequest {
    private String username;

    public ClientUpdateUserRequest() {
    }

    public ClientUpdateUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean validate() {
        boolean valid = this.username != null && !this.username.equals("");
        return valid;
    }
}
