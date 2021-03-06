package com.mpolder.jarche.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String code;

    public User() {
    }

    public User(UUID id, String username, String code) {
        this.id = id;
        this.username = username;
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCode() { return code; }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean validate() {
        boolean valid = this.id != null && !this.id.toString().equals("");
        valid &= this.username != null && !this.username.equals("");
        valid &= this.code != null && !this.code.equals("");
        return valid;
    }
}
