package com.mggcode.gestion_bd_elecciones.repository.autonomicas;

import com.mggcode.gestion_bd_elecciones.model.autonomicas.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APartidoRepository extends JpaRepository<Partido, String> {

}
