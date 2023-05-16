package com.mggcode.gestion_bd_elecciones.mapper.municipales;


import com.mggcode.gestion_bd_elecciones.DTO.municipales.CircunscripcionDTO;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;


public class CircunscripcionDTOMapper {

    public CircunscripcionDTO toDTO(Circunscripcion c, Circunscripcion espania, String literalParticipacion, String anioUltimas) {
        double participacion = 0.0;
        double participacionHistorica = 0.0;
        double participacionMedia = 0.0;
        String literal = literalParticipacion;

        if (c.getParticipacion() != 0) {
            participacion = c.getParticipacion();
            participacionHistorica = c.getParticipacionHist();
            participacionMedia = espania.getParticipacion();
        } else if (c.getAvance3() != 0) {
            participacion = c.getAvance3();
            participacionHistorica = c.getAvance3Hist();
            participacionMedia = espania.getAvance3();
            literal += " 11h";
        } else if (c.getAvance2() != 0) {
            participacion = c.getAvance2();
            participacionHistorica = c.getAvance2Hist();
            participacionMedia = espania.getAvance2();
            literal += " 13h";
        } else {
            participacion = c.getAvance1();
            participacionHistorica = c.getAvance1Hist();
            participacionMedia = espania.getAvance3();
            literal += " 18h";
        }


        return CircunscripcionDTO.builder()
                .codigo(c.getCodigo())
                .codigoComunidad(c.getCodigoComunidad())
                .codigoProvincia(c.getCodigoProvincia())
                .codigoMunicipio(c.getCodigoMunicipio())
                .nombreCircunscripcion(c.getNombreCircunscripcion())
                .escrutado(c.getEscrutado())
                .escanios(c.getEscanios())
                .participacion(participacion)
                .participacionHistorico(participacionHistorica)
                .literalParticipacion(literal)
                .participacionMedia(participacionMedia)
                .anioUltimosDatos(anioUltimas)
                .votantes(c.getVotantes())
                .escaniosHistoricos(c.getEscaniosHistoricos())
                .mayoria(getMayoria(c.getEscanios()))
                .autonomiaOMunicipio(0)
                .participacionHist(0.0)
                .build();
    }

    private int getMayoria(int escanios) {
        return (escanios / 2) + 1;
    }

}
