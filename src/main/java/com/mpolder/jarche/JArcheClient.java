package com.mpolder.jarche;

import com.mpolder.jarche.base.BasicWebSocketClient;
import com.mpolder.jarche.base.ConnectionEvent;
import com.mpolder.jarche.interfaces.FeatureProfile;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.request.ResponseHandler;
import com.mpolder.jarche.interfaces.IEventHandler;

import java.net.URI;
import java.net.URISyntaxException;

public class JArcheClient {
    BasicWebSocketClient client;
    JArcheBackend backend;

    public JArcheClient(String ip, int port) throws URISyntaxException {
        this.client = new BasicWebSocketClient(new URI(ip + ":" + port));
        this.backend = new JArcheBackend();
        registerListeners();
    }

    public ResponseHandler send(IRequest request) {
        return backend.send(client.getConnection(), request);
    }

    public void connect() {
        client.connect();
    }

    public void close() {
        if (!client.isClosed()) {
            client.close();
        }
    }

    public void registerProfile(FeatureProfile profile) {
        backend.registerProfile(profile);
    }

    public void registerHandler(IEventHandler handler) {
        backend.registerHandler(handler);
    }

    private void registerListeners() {
        client.addEventListener(ConnectionEvent.MESSAGE, ((socket, data) -> {
            backend.handleRequest(socket, (String) data);
            backend.handleConfirmation((String) data);
        }));
        client.addEventListener(ConnectionEvent.CONNECT, (socket, data) -> backend.handleConnect(socket));
        client.addEventListener(ConnectionEvent.DISCONNECT, (socket, data) -> backend.handleDisconnect(socket));
    }
}