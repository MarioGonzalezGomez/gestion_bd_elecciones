package com.mggcode.gestion_bd_elecciones.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebSocketHandler extends TextWebSocketHandler implements WebSocketSender, SubProtocolCapable {

    Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    String entity = "";

    public WebSocketHandler(String entity) {
        this.entity = entity;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Conexión establecida. Sesión: " + session);
        sessions.add(session);
        var message = new TextMessage("Web socket" + entity);
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Conexión cerrada: " + status);
        sessions.remove(session);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Enviando mensaje");
        sessions.forEach(session -> {
            try {
                System.out.println("Enviando mensaje a: "+ session);
                System.out.println(session.isOpen());
                var txtMessage = new TextMessage(message);
                session.sendMessage(txtMessage);

            } catch (IOException e) {
                System.err.println("Error de envio de mensaje");
                throw new RuntimeException(e);
            }
        });
    }

    @Scheduled(fixedRate = 5000)
    @Override
    public void sendPeriodicMessage() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                var broadcast = "server periodic message" + LocalDateTime.now();
                session.sendMessage(new TextMessage(broadcast));
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.err.println("Error de transporte: " + exception.getMessage());
    }

    @Override
    public List<String> getSubProtocols() {
        return null;
    }
}
