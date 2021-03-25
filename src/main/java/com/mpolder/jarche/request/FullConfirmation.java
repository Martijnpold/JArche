package com.mpolder.jarche.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class FullConfirmation {
    private UUID id;
    private JsonNode confirmation;

    public FullConfirmation(UUID id, JsonNode confirmation) {
        this.id = id;
        this.confirmation = confirmation;
    }
}
