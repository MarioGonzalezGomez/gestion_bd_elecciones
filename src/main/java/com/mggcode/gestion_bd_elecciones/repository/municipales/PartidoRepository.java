package com.mggcode.gestion_bd_elecciones.repository.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, String> {

}
