package com.mggcode.gestion_bd_elecciones.repository.autonomicas;

import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ACircunscripcionRepository extends JpaRepository<Circunscripcion, String> {
    @Query("SELECT c FROM Circunscripcion c WHERE c.codigo = ?1")
    Circunscripcion findCircunscripcionById(String cod);

    @Query("SELECT c FROM Circunscripcion  c")
    List<Circunscripcion> findAllCircunscripciones();

}
