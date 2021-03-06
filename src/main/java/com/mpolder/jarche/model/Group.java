package com.mpolder.jarche.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Group {
    private UUID id;
    private String name;
    private String password;
    private User creator;
    private List<User> users;

    public Group() {
    }

    public Group(UUID id, String name, User creator, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.creator = creator;
        this.users = new ArrayList<>(Collections.singleton(creator));
    }

    public User getCreator() {
        return creator;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUsers(User... users) {
        for (User u : users) {
            if (!this.users.contains(u)) {
                this.users.add(u);
            }
        }
    }

    public void addUsers(List<User> users) {
        this.addUsers(users.toArray(new User[0]));
    }

    public boolean removeUser(User user) {
        return this.users.remove(user);
    }

    /**
     * Copy the group into an instance that does not contain a password
     * @return A password-less group
     */
    public Group safeCopy() {
        Group copy = new Group(id, name, creator, null);
        copy.addUsers(users);
        return copy;
    }

    public boolean validate() {
        boolean valid = this.id != null && !this.id.toString().equals("");
        valid &= this.name != null && !this.name.equals("");
        valid &= this.password != null && !this.password.equals("");
        valid &= this.creator != null && this.creator.validate();
        return valid;
    }
}
