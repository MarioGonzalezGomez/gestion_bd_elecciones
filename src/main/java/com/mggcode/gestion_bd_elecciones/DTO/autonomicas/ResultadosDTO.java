package com.mggcode.gestion_bd_elecciones.DTO.autonomicas;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultadosDTO {
    private CircunscripcionResultadosDTO ccaa;
    private List<ProvinciaResultadosDTO> provincias;
}
