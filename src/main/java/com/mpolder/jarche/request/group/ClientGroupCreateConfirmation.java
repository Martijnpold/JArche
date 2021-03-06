package com.mpolder.jarche.request.group;

import com.mpolder.jarche.enums.Status;
import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.model.Group;

public class ClientGroupCreateConfirmation implements IConfirmation {
    private Group group;
    private Status status;

    public ClientGroupCreateConfirmation() {
    }

    public ClientGroupCreateConfirmation(Group group, Status status) {
        this.group = group;
        this.status = status;
    }

    public Group getGroup() {
        return group;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean validate() {
        boolean valid = this.group != null && this.status != null;
        assert group != null;
        valid &= group.validate();
        return valid;
    }
}
