package com.mpolder.jarche.request.group;

import com.mpolder.jarche.enums.Status;
import com.mpolder.jarche.interfaces.IConfirmation;

public class ClientGroupLeaveConfirmation implements IConfirmation {
    private Status status;

    public ClientGroupLeaveConfirmation() {
    }

    public ClientGroupLeaveConfirmation(Status status) {
        this.status = status;
    }

    public Status getStatus() { return status; }

    @Override
    public boolean validate() {
        boolean valid = this.status != null;
        return valid;
    }
}
