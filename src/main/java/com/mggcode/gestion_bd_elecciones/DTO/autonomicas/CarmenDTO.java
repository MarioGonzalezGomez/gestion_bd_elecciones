package com.mggcode.gestion_bd_elecciones.DTO.autonomicas;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarmenDTO {
    private CircunscripcionDTO circunscripcion;
    private int numPartidos;
    private List<CpDTO> cpDTO;
}
