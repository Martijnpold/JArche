package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.model.Group;
import com.mpolder.jarche.model.User;

public class ServerGroupUpdateRequest implements IRequest {
    private Group group;
    private User self;

    public ServerGroupUpdateRequest() {
    }

    public ServerGroupUpdateRequest(Group group, User user) {
        this.group = group;
        this.self = user;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public boolean validate() {
        boolean valid = this.group == null || this.group.validate();
        valid &= this.self.validate();
        return valid;
    }
}
