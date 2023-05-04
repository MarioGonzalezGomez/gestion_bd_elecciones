package com.mggcode.gestion_bd_elecciones.repository.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.Literal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiteralRepository extends JpaRepository<Literal, String> {
}
