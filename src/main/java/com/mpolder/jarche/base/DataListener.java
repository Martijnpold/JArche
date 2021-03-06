package com.mpolder.jarche.base;

import com.mpolder.jarche.JArcheServer;
import org.java_websocket.WebSocket;

public interface DataListener {
    void onData(JArcheServer server, WebSocket socket, Object data);
}
