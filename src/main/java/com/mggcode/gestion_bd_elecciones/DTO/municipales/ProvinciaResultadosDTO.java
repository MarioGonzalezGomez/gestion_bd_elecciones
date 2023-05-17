package com.mggcode.gestion_bd_elecciones.DTO.municipales;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProvinciaResultadosDTO {
    private String codigo;
    private String nombre;
    private String codPartidoGanador;
    private String nomPartidoGanador;
}
