package com.mggcode.gestion_bd_elecciones.mapper.autonomicas;

import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CircunscripcionResultadosDTO;

import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;

public class CircunscripcionResultadosDTOMapper {

    public CircunscripcionResultadosDTO toDTO(Circunscripcion c, int numProvincias) {
        return CircunscripcionResultadosDTO.builder()
                .codigo(c.getCodigo())
                .nombre(c.getNombreCircunscripcion())
                .numProvincias(numProvincias)
                .build();
    }
}
