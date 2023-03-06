package com.mggcode.gestion_bd_elecciones.repository.autonomicas;

import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACircunscripcionRepository extends JpaRepository<Circunscripcion, String> {
}
