package com.mpolder.jarche.example.request;

import com.mpolder.jarche.interfaces.IConfirmation;

public class HelloWorldConfirmation implements IConfirmation {
    private String message;

    public HelloWorldConfirmation() {
    }

    public HelloWorldConfirmation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean validate() {
        return this.message != null && !this.message.equals("");
    }
}
