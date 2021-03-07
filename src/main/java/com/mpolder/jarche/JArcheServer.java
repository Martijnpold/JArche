package com.mpolder.jarche;

import com.mpolder.jarche.base.BasicWebSocketServer;
import com.mpolder.jarche.base.ConnectionEvent;
import com.mpolder.jarche.interfaces.FeatureProfile;
import com.mpolder.jarche.interfaces.IRequest;
import com.mpolder.jarche.request.ResponseHandler;
import com.mpolder.jarche.request.handler.IEventHandler;
import org.java_websocket.WebSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JArcheServer {
    BasicWebSocketServer server;
    JArcheBackend backend;

    public JArcheServer(int port) {
        this.server = new BasicWebSocketServer(new InetSocketAddress(port));
        this.backend = new JArcheBackend();
    }

    public ResponseHandler send(WebSocket socket, IRequest request) {
        return backend.send(socket, request);
    }

    public List<ResponseHandler> sendAll(IRequest request) {
        return getConnections().parallelStream().map(x -> backend.send(x, request)).collect(Collectors.toList());
    }

    public Collection<WebSocket> getConnections() {
        return server.getConnections();
    }

    public void start() {
        server.start();
    }

    public void stop() throws IOException, InterruptedException {
        server.stop();
    }

    public void registerProfile(FeatureProfile profile) {
        backend.registerProfile(profile);
    }

    public void registerHandler(IEventHandler handler) {
        backend.registerHandler(handler);
    }

    public void registerListeners() {
        server.addEventListener(ConnectionEvent.MESSAGE, ((socket, data) -> {
            backend.handleRequest(socket, (String) data);
            backend.handleConfirmation((String) data);
        }));
        server.addEventListener(ConnectionEvent.CONNECT, (socket, data) -> backend.handleConnect(socket));
        server.addEventListener(ConnectionEvent.DISCONNECT, (socket, data) -> backend.handleDisconnect(socket));
    }
}