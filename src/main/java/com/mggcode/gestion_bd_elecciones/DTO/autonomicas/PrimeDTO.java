package com.mggcode.gestion_bd_elecciones.DTO.autonomicas;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrimeDTO {
    private String nombreCircunscripcion;
    private String codigoCircunscripcion;
    private int escaniosTotales;
    private double escrutado;
    private List<CpPrimeDTO> cps;
}
