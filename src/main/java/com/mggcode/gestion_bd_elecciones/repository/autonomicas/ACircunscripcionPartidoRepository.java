package com.mggcode.gestion_bd_elecciones.repository.autonomicas;


import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ACircunscripcionPartidoRepository extends JpaRepository<CircunscripcionPartido, Key> {

    List<CircunscripcionPartido> findByKey_Circunscripcion(String codCircunscripcion);

    List<CircunscripcionPartido> findByKey_Partido(String codPartido);

}
