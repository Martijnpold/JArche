package com.mpolder.jarche.interfaces;

public interface IRequestPair {
    Class<? extends IEventHandler> handler();

    Class<? extends IRequest> request();

    Class<? extends IConfirmation> confirmation();
}
