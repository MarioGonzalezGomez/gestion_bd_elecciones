package com.mggcode.gestion_bd_elecciones.mapper.autonomicas;


import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CircunscripcionDTO;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.service.autonomicas.ACircunscripcionService;

public class CircunscripcionDTOMapper {

    private final ACircunscripcionService serv;

    public CircunscripcionDTOMapper() {
        serv = new ACircunscripcionService();
    }

    public CircunscripcionDTO toDTO(Circunscripcion c, String literalParticipacion, String anioUltimas) {
        Circunscripcion espania = serv.findById("9900000");
        double participacion;
        double participacionHistorica;
        double participacionMedia;
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
                .avance2Hist(0.0)
                .avance3Hist(0.0)
                .participacionHist(0.0)
                .build();
    }


}
