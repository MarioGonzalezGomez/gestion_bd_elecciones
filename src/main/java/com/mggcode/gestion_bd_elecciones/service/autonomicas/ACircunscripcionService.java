package com.mggcode.gestion_bd_elecciones.service.autonomicas;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.mggcode.gestion_bd_elecciones.config.WebSocketConfig;
import com.mggcode.gestion_bd_elecciones.config.WebSocketHandler;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.repository.autonomicas.ACircunscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ACircunscripcionService implements IBaseService<Circunscripcion> {

    @Autowired
    private ACircunscripcionRepository circunscripcionRepository;

    @Autowired
    private WebSocketConfig webSocketConfig;

    private WebSocketHandler webSocketService = null;

    @Override
    public Circunscripcion create(Circunscripcion circunscripcion) {

        var res = circunscripcionRepository.save(circunscripcion);
        onChange("CREATE", circunscripcion);
        return res;
    }

    @Override
    public Circunscripcion update(Circunscripcion circunscripcion) {
        var res = circunscripcionRepository.save(circunscripcion);
        onChange("UPDATE", circunscripcion);
        return circunscripcionRepository.save(circunscripcion);
    }

    @Override
    public Circunscripcion findById(String id) {
        Optional<Circunscripcion> circunscripcionOptional = circunscripcionRepository.findById(id);
        return circunscripcionOptional.orElse(null);
    }

    @Override
    public List<Circunscripcion> findAll() {
        return circunscripcionRepository.findAll();
    }

    @Override
    public void delete(String id) {
        circunscripcionRepository.deleteById(id);
    }

    private void onChange(String type, Circunscripcion data) {
        var message = type + ':' + data.getCodigo();
        if(webSocketService == null)
            webSocketService = webSocketConfig.autonomicasCircunscripcionesHandler();
        System.out.println("Enviando cambio");
        webSocketService.sendMessage(message);
    }
}
