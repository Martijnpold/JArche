package com.mpolder.jarche.example.profile;

import com.mpolder.jarche.example.request.HelloWorldPair;
import com.mpolder.jarche.interfaces.FeatureProfile;

public class HelloWorldProfile extends FeatureProfile {
    @Override
    protected void loadRequests() {
        addPair(new HelloWorldPair());
    }
}
