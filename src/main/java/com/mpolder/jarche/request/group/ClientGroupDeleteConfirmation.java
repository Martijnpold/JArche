package com.mpolder.jarche.request.group;

import com.mpolder.jarche.enums.Status;
import com.mpolder.jarche.interfaces.IConfirmation;

public class ClientGroupDeleteConfirmation implements IConfirmation {
    private Status status;

    public ClientGroupDeleteConfirmation() {
    }

    public ClientGroupDeleteConfirmation(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean validate() {
        boolean valid = this.status != null;
        return valid;
    }
}
