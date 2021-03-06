package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IRequest;

import java.util.UUID;

public class ClientGroupLeaveRequest implements IRequest {
    private UUID id;

    public ClientGroupLeaveRequest() {
    }

    public ClientGroupLeaveRequest(UUID id) {
        this.id = id;
    }

    public UUID getId() { return id; }

    @Override
    public boolean validate() {
        boolean valid = this.id != null && !this.id.toString().equals("");
        return valid;
    }
}
