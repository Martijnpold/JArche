package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.example.request.HelloWorldPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PairCache {
    private final HashMap<Class<? extends IRequest>, IRequestPair> pairByRequest;
    private final HashMap<Class<? extends IConfirmation>, IRequestPair> pairByConfirmation;
    private final HashMap<String, IRequestPair> pairByName;

    public PairCache() {
        pairByRequest = new HashMap<>();
        pairByConfirmation = new HashMap<>();
        pairByName = new HashMap<>();
        register(new HelloWorldPair());
    }

    public void register(IRequestPair pair) {
        pairByRequest.put(pair.request(), pair);
        pairByConfirmation.put(pair.confirmation(), pair);
        pairByName.put(pair.request().getSimpleName(), pair);
    }

    public List<IRequestPair> getAll() {
        return new ArrayList<>(pairByRequest.values());
    }

    public IRequestPair get(IRequest request) {
        return pairByRequest.get(request.getClass());
    }

    public IRequestPair get(IConfirmation confirmation) {
        return pairByConfirmation.get(confirmation.getClass());
    }

    public IRequestPair get(String type) {
        return pairByName.get(type);
    }
}
