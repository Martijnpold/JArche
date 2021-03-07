package com.mpolder.jarche.base;

import org.java_websocket.WebSocket;

public interface DataListener {
    void onData(WebSocket socket, Object data);
}
