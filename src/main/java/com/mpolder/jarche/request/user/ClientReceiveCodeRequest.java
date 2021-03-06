package com.mpolder.jarche.request.user;

import com.mpolder.jarche.interfaces.IRequest;

public class ClientReceiveCodeRequest implements IRequest {
    public ClientReceiveCodeRequest() {}

    @Override
    public boolean validate() {
        return true;
    }
}
