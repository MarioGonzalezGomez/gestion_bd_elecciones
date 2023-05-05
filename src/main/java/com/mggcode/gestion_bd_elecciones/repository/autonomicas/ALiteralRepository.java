package com.mggcode.gestion_bd_elecciones.repository.autonomicas;

import com.mggcode.gestion_bd_elecciones.model.autonomicas.Literal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ALiteralRepository extends JpaRepository<Literal, String> {
}
