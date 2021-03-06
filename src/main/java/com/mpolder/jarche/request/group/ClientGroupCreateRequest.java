package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IRequest;

public class ClientGroupCreateRequest implements IRequest {
    private String name;
    private String password;

    public ClientGroupCreateRequest() {
    }

    public ClientGroupCreateRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean validate() {
        boolean valid = this.name != null && this.password != null;
        valid &= !this.name.equals("") && !this.password.equals("");
        return valid;
    }
}
