package com.mpolder.jarche.request.helloworld;

import com.mpolder.jarche.interfaces.IRequest;

public class HelloWorldRequest implements IRequest {
    private String message;

    public HelloWorldRequest() {
    }

    public HelloWorldRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean validate() {
        boolean valid = this.message != null && !this.message.equals("");
        return false;
    }
}
