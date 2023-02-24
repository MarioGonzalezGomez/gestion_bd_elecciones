package com.mggcode.gestion_bd_elecciones.repository;

import com.mggcode.gestion_bd_elecciones.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, String> {

}
