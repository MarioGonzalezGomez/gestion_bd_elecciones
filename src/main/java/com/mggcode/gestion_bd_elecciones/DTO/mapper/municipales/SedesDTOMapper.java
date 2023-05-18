package com.mggcode.gestion_bd_elecciones.DTO.mapper.municipales;

import com.mggcode.gestion_bd_elecciones.DTO.municipales.SedesDTO;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;

public class SedesDTOMapper {

    public SedesDTO toDTO(CircunscripcionPartido cp, Partido p) {

        return SedesDTO.builder()
                .codigoPartido(p.getCodigo())
                .codigoPadre(p.getCodigoPadre())
                .escanos_desde(cp.getEscanos_desde())
                .escanos_hasta(cp.getEscanos_hasta())
                .escanos_hist(cp.getEscanos_hasta_hist())
                .porcentajeVoto(cp.getPorcentajeVoto())
                .porcentajeVotoHistorico(cp.getVotantesHistorico())
                .numVotantes(cp.getNumVotantes())
                .siglas(p.getSiglas())
                .literalPartido(p.getNombreCompleto())
                .numVotantes_hist(cp.getNumVotantesHistorico())
                .build();
    }
}
