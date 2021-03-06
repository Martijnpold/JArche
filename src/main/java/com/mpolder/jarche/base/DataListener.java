package com.mpolder.jarche.base;

import com.mpolder.jarche.AuctionServer;
import org.java_websocket.WebSocket;

public interface DataListener {
    void onData(AuctionServer server, WebSocket socket, Object data);
}
