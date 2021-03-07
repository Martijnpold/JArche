package com.mpolder.jarche.example.request;

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
        return this.message != null;
    }
}
