package com.mggcode.gestion_bd_elecciones.service.autonomicas;

import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.repository.autonomicas.ACircunscripcionRepository;
import io.debezium.jdbc.JdbcConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ACircunscripcionService implements IBaseService<Circunscripcion> {

    @Autowired
    private ACircunscripcionRepository circunscripcionRepository;


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

        Circunscripcion circunscripcionOptional = circunscripcionRepository.findCircunscripcionById(id);
        return circunscripcionOptional;
    }

    @Override
    public List<Circunscripcion> findAll() {
        return circunscripcionRepository.findAllCircunscripciones();
    }

    @Override
    public void delete(String id) {
        circunscripcionRepository.deleteById(id);
    }


}
