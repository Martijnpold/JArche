package com.mpolder.jarche.request;

import java.util.HashMap;
import java.util.UUID;

public class RequestCache {
    private final HashMap<UUID, SentRequest> cache;

    public RequestCache() {
        cache = new HashMap<>();
    }

    public void cache(SentRequest sentRequest) {
        cache.put(sentRequest.getId(), sentRequest);
    }

    public SentRequest decache(UUID uuid) {
        return cache.remove(uuid);
    }
}
