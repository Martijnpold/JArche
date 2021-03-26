package com.mpolder.jarche;

import com.mpolder.jarche.base.BasicWebSocketClient;
import com.mpolder.jarche.base.ConnectionEvent;
import com.mpolder.jarche.interfaces.FeatureProfile;
import com.mpolder.jarche.interfaces.IEventHandler;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.request.ResponseHandler;

import java.net.URI;
import java.net.URISyntaxException;

public class JArcheClient {
    private final URI uri;
    private BasicWebSocketClient client;
    private final JArcheBackend backend;

    public JArcheClient(String ip, int port) throws URISyntaxException {
        this.uri = new URI(ip + ":" + port);
        this.backend = new JArcheBackend();
    }

    public ResponseHandler send(IRequest request) {
        return backend.send(client.getConnection(), request);
    }

    public void connect() {
        try {
            if (client != null) client.close();
            client = new BasicWebSocketClient(uri);
            registerListeners();
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reconnect() {
        client.reconnect();
    }

    public boolean reconnectBlocking() {
        try {
            return client.reconnectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        if (client != null) client.close();
    }

    public boolean isOpen() {
        return client != null && client.isOpen();
    }

    public void registerProfile(FeatureProfile profile) {
        backend.registerProfile(profile);
    }

    public void registerHandler(IEventHandler handler) {
        backend.registerHandler(handler);
    }

    private void registerListeners() {
        client.addEventListener(ConnectionEvent.MESSAGE, ((socket, data) -> backend.handleData(socket, (String) data)));
        client.addEventListener(ConnectionEvent.CONNECT, (socket, data) -> backend.handleConnect(socket));
        client.addEventListener(ConnectionEvent.DISCONNECT, (socket, data) -> backend.handleDisconnect(socket));
    }
}