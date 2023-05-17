package com.mggcode.gestion_bd_elecciones.mapper.municipales;

import com.mggcode.gestion_bd_elecciones.DTO.municipales.CircunscripcionResultadosDTO;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;

public class CircunscripcionResultadosDTOMapper {

    public CircunscripcionResultadosDTO toDTO(Circunscripcion c, int numProvincias) {
        return CircunscripcionResultadosDTO.builder()
                .codigo(c.getCodigo())
                .nombre(c.getNombreCircunscripcion())
                .numProvincias(numProvincias)
                .build();
    }
}
