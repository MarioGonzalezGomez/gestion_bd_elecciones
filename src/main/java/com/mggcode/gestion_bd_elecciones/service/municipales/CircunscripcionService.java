package com.mggcode.gestion_bd_elecciones.service.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.repository.municipales.CircunscripcionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CircunscripcionService implements IBaseService<Circunscripcion> {

    @Autowired
    private CircunscripcionRepository circunscripcionRepository;

    @Override
    public Circunscripcion create(Circunscripcion circunscripcion) {
        return circunscripcionRepository.save(circunscripcion);
    }

    @Override
    public Circunscripcion update(Circunscripcion circunscripcion) {
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
}
