package com.mggcode.gestion_bd_elecciones.DTO.municipales;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CpPrimeDTO {
    private String codigoPartido;
    private int escanios;
    private int escaniosHistorico;
    private String color;
}
