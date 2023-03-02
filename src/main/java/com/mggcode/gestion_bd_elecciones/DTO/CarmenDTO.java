package com.mggcode.gestion_bd_elecciones.DTO;

import com.mggcode.gestion_bd_elecciones.model.Circunscripcion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarmenDTO {
    private Circunscripcion circunscripcion;
    private int numPartidos;
    private List<CpDTO> cpDTO;

   // private String nombreCircunscripcion;
   // private int escaniosTotales;
   // private int numPartidos;
   // private double escrutadoActual;
   // private double escrutadoAnterior;
   // private String autonomia;
   // private String circunscripcion;
   // private String municipio;
//
   // private String codigo;
   // private String codigoPadre;
   // private int escDesde;
   // private int escHasta;
   // private int escDesdeHist;
   // private int escHastaHist;
   // private double porcentajeVoto;
   // private double porcentajeVotoHist;
   // private int votantes;
   // private String siglas;
   // private String literal;


}
