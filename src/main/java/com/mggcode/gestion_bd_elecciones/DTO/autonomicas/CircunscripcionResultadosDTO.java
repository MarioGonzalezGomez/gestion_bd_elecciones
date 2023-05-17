package com.mggcode.gestion_bd_elecciones.DTO.autonomicas;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CircunscripcionResultadosDTO {
    private String codigo;
    private String nombre;
    private int numProvincias;
}
