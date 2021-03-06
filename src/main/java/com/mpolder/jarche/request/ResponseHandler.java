package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.IConfirmation;
import lombok.Getter;

import java.util.UUID;
import java.util.function.Consumer;

public class ResponseHandler {
    @Getter
    private final UUID id;
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
}
