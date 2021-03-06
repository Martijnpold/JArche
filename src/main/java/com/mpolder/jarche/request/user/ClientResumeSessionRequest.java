package com.mpolder.jarche.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.mpolder.jarche.interfaces.IRequest;

import java.util.UUID;

public class ClientResumeSessionRequest implements IRequest {
    @SerializedName("user_id")
    private UUID userId;

    public ClientResumeSessionRequest() {
    }

    public ClientResumeSessionRequest(UUID userId) {
        this.userId = userId;
    }

    @JsonProperty("user_id")
    public UUID getUserId() {
        return userId;
    }

    @Override
    public boolean validate() {
        return userId != null;
    }
}
