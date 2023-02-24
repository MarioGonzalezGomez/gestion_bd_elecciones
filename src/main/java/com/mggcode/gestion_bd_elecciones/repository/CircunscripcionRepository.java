package com.mggcode.gestion_bd_elecciones.repository;

import com.mggcode.gestion_bd_elecciones.model.Circunscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircunscripcionRepository extends JpaRepository<Circunscripcion, String> {
}
