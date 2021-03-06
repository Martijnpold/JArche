package com.mpolder.jarche.request.user;

import com.mpolder.jarche.enums.Status;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.model.User;

public class ClientUpdateUserConfirmation implements IConfirmation {
    private User user;
    private Status status;

    public ClientUpdateUserConfirmation() {
    }

    public ClientUpdateUserConfirmation(User user, Status status) {
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
        boolean valid = this.user != null && this.status != null;
        assert user != null;
        valid &= user.validate();
        return valid;
    }
}
