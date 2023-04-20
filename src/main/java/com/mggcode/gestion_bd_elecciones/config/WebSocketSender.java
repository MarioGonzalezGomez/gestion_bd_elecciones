package com.mggcode.gestion_bd_elecciones.config;

import java.io.IOException;

public interface WebSocketSender {
    void sendMessage(String message);
    void sendPeriodicMessage() throws IOException;
}
