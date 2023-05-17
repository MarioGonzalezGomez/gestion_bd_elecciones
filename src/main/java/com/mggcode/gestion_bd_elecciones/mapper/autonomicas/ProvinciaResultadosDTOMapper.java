package com.mggcode.gestion_bd_elecciones.mapper.autonomicas;

import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.ProvinciaResultadosDTO;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;


public class ProvinciaResultadosDTOMapper {

    public ProvinciaResultadosDTO toDTO(CircunscripcionPartido cp, String nombreCircunscripcion, String nombreGanador) {
        return ProvinciaResultadosDTO.builder()
                .codigo(cp.getKey().getCircunscripcion())
                .nombre(nombreCircunscripcion)
                .codPartidoGanador(cp.getKey().getPartido())
                .nomPartidoGanador(nombreGanador)
                .build();
    }
}
