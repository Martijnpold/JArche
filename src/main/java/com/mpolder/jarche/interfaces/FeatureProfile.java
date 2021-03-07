package com.mpolder.jarche.interfaces;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class FeatureProfile {
    @Getter
    private final List<IRequestPair> requests;

    public FeatureProfile() {
        requests = new ArrayList<>();
        loadRequests();
    }

    public void addPair(IRequestPair pair) {
        requests.add(pair);
    }

    protected abstract void loadRequests();
}
