package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.IConfirmation;

import java.util.UUID;
import java.util.function.Consumer;

public class ResponseHandler {
    private UUID id;
    private Consumer<IConfirmation> onResponse;

    public ResponseHandler(UUID id) {
        this.id = id;
    }

    public void onResponse(Consumer<IConfirmation> onResponse) {
        this.onResponse = onResponse;
    }

    public void resolve(IConfirmation confirmation) {
        if (onResponse != null) {
            onResponse.accept(confirmation);
        }
    }

    public UUID getId() {
        return id;
    }
}
