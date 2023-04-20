package com.mggcode.gestion_bd_elecciones.mapper.autonomicas;

import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.SedesDTO;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Partido;

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
