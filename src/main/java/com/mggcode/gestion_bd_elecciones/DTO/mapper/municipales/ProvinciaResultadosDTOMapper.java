package com.mggcode.gestion_bd_elecciones.DTO.mapper.municipales;

import com.mggcode.gestion_bd_elecciones.DTO.municipales.ProvinciaResultadosDTO;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;


public class ProvinciaResultadosDTOMapper {

    public ProvinciaResultadosDTO toDTO(CircunscripcionPartido cp, String nombreCircunscripcion, String nombreGanador) {
        return ProvinciaResultadosDTO.builder()
                .codigo(cp.getKey().getCircunscripcion().substring(2, 4))
                .nombre(nombreCircunscripcion)
                .codPartidoGanador(cp.getKey().getPartido())
                .nomPartidoGanador(nombreGanador)
                .build();
    }
}
