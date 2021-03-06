package com.mpolder.jarche.request.user;

import com.mpolder.jarche.enums.Status;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.model.User;

public class ClientResumeSessionConfirmation implements IConfirmation {
    private User user;
    private Status status;

    public ClientResumeSessionConfirmation() {
    }

    public ClientResumeSessionConfirmation(User user, Status status) {
        this.user = user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean validate() {
        return user != null && status != null && user.validate();
    }
}
