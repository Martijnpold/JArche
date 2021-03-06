package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IRequest;

import java.util.UUID;

public class ClientGroupJoinRequest implements IRequest {
    private UUID id;
    private String password;

    public ClientGroupJoinRequest() {
    }

    public ClientGroupJoinRequest(UUID id, String password) {
        this.id = id;
        this.password = password;
    }

    public UUID getId() { return id; }

    public String getPassword() { return password; }

    @Override
    public boolean validate() {
        boolean valid = this.id != null && !this.id.toString().equals("");
        valid &= this.password != null && !password.equals("");
        return valid;
    }
}
