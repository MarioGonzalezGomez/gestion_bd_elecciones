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
}
