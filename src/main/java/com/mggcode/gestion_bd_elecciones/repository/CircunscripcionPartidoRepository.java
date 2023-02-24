package com.mggcode.gestion_bd_elecciones.repository;


import com.mggcode.gestion_bd_elecciones.model.CircunscripcionPartido;

import com.mggcode.gestion_bd_elecciones.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CircunscripcionPartidoRepository extends JpaRepository<CircunscripcionPartido, Key> {

    List<CircunscripcionPartido> findByKey_Circunscripcion(String codCircunscripcion);

    List<CircunscripcionPartido> findByKey_Partido(String codPartido);

}
