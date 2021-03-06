package com.mpolder.jarche.request.group;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.model.Group;

import java.util.List;

public class ClientGroupListConfirmation implements IConfirmation {
    private List<Group> groups;

    public ClientGroupListConfirmation() {
    }

    public ClientGroupListConfirmation(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public boolean validate() {
        boolean valid = groups != null;
        return valid;
    }
}
