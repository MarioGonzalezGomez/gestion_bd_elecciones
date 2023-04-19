package com.mggcode.gestion_bd_elecciones.DTO.municipales;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CpDTO {
    private String codigoPartido;
    private String codigoPadre;
    private int escanos_desde;
    private int escanos_hasta;
    private int escanos_hasta_hist;
    private double porcentajeVoto;
    private double porcentajeVotoHistorico;
    private int numVotantes;
    private String siglas;
    private String literalPartido;
    private double posicionInicial;
    private double aperturaArco;
    private double posicionInicialDesdeSondeo;
    private double aperturaArcoDesdeSondeo;
    private double posicionInicialHastaSondeo;
    private double aperturaArcoHastaSondeo;
    private double escanos_desde_sondeo;
    private double escanos_hasta_sondeo;
    private double porcentajeVotoSondeo;

}
