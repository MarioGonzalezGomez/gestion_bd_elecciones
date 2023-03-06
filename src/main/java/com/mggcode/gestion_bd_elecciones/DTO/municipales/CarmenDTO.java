package com.mggcode.gestion_bd_elecciones.DTO.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarmenDTO {
    private Circunscripcion circunscripcion;
    private int numPartidos;
    private List<CpDTO> cpDTO;
}
