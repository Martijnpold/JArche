package com.mpolder.jarche.interfaces;

import com.mpolder.jarche.request.handler.IEventHandler;

public interface IRequestPair {
    Class<? extends IEventHandler> handler();

    Class<? extends IRequest> request();

    Class<? extends IConfirmation> confirmation();
}
