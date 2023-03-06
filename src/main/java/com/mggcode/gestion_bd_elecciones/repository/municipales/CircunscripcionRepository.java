package com.mggcode.gestion_bd_elecciones.repository.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircunscripcionRepository extends JpaRepository<Circunscripcion, String> {
}
