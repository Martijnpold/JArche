package com.mpolder.jarche.request;

import com.mpolder.jarche.interfaces.IConfirmation;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.interfaces.IRequestPair;
import com.mpolder.jarche.request.auction.ClientPurchaseRoundPair;
import com.mpolder.jarche.request.chat.ClientChatPostPair;
import com.mpolder.jarche.request.group.*;
import com.mpolder.jarche.request.helloworld.HelloWorldPair;
import com.mpolder.jarche.request.user.ClientCoupleUserPair;
import com.mpolder.jarche.request.user.ClientReceiveCodePair;
import com.mpolder.jarche.request.user.ClientResumeSessionPair;
import com.mpolder.jarche.request.user.ClientUpdateUserPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PairCache {
    private HashMap<Class<? extends IRequest>, IRequestPair> pairByRequest;
    private HashMap<Class<? extends IConfirmation>, IRequestPair> pairByConfirmation;
    private HashMap<String, IRequestPair> pairByName;

    public PairCache() {
        pairByRequest = new HashMap<>();
        pairByConfirmation = new HashMap<>();
        pairByName = new HashMap<>();
        build();
    }

    private void build() {
        register(new HelloWorldPair());
        register(new ClientChatPostPair());
        register(new ClientGroupListPair());
        register(new ClientGroupCreatePair());
        register(new ClientGroupDeletePair());
        register(new ClientGroupJoinPair());
        register(new ClientGroupLeavePair());
        register(new ClientUpdateUserPair());
        register(new ClientCoupleUserPair());
        register(new ClientPurchaseRoundPair());
        register(new ClientReceiveCodePair());
        register(new ClientResumeSessionPair());
    }

    private void register(IRequestPair pair) {
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
