package com.mggcode.gestion_bd_elecciones.DTO.municipales;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CarmenDtoList {
    private int size;
    private List<CarmenDTO> carmenDTOList;
}
