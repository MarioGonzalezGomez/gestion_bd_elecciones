package com.mggcode.gestion_bd_elecciones.repository.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CircunscripcionRepository extends JpaRepository<Circunscripcion, String> {
    @Query("SELECT c FROM Circunscripcion c WHERE c.codigo = ?1")
    Circunscripcion findCircunscripcionById(String cod);

    @Query("SELECT c FROM Circunscripcion  c")
    List<Circunscripcion> findAllCircunscripciones();

}
