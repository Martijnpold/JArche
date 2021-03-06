package com.mpolder.jarche.request.group;

import com.mpolder.jarche.enums.Status;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.model.Group;

public class ClientGroupJoinConfirmation implements IConfirmation {
    private Group group;
    private Status status;

    public ClientGroupJoinConfirmation() {}

    public ClientGroupJoinConfirmation(Group group, Status status) {
        this.group = group;
        this.status = status;
    }

    @Override
    public boolean validate() {
        boolean valid = status != null;
        return valid;
    }
}
