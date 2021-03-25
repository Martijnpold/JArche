package com.mpolder.jarche.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class FullRequest {
    private UUID id;
    private String type;
    private JsonNode request;

    public FullRequest(UUID id, String type, JsonNode request) {
        this.id = id;
        this.type = type;
        this.request = request;
    }
}
