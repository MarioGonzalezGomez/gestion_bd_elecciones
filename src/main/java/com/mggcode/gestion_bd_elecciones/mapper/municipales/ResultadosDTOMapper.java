package com.mggcode.gestion_bd_elecciones.mapper.municipales;


import com.mggcode.gestion_bd_elecciones.DTO.municipales.CircunscripcionResultadosDTO;
import com.mggcode.gestion_bd_elecciones.DTO.municipales.ProvinciaResultadosDTO;
import com.mggcode.gestion_bd_elecciones.DTO.municipales.ResultadosDTO;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;

import java.util.ArrayList;
import java.util.List;


public class ResultadosDTOMapper {

    public ResultadosDTO toDTO(Circunscripcion c, List<CircunscripcionPartido> cps, List<String> nombresProvincias, List<String> nombresGanadores) {
        CircunscripcionResultadosDTOMapper mapperCir = new CircunscripcionResultadosDTOMapper();
        ProvinciaResultadosDTOMapper mapperPro = new ProvinciaResultadosDTOMapper();
        CircunscripcionResultadosDTO ccaa = mapperCir.toDTO(c, cps.size());

        List<ProvinciaResultadosDTO> provincias = new ArrayList<>();
        cps.forEach(cp -> {
            ProvinciaResultadosDTO provincia = mapperPro.toDTO(cp, nombresProvincias.get(cps.indexOf(cp)), nombresGanadores.get(cps.indexOf(cp)));
            provincias.add(provincia);
        });

        return ResultadosDTO.builder()
                .ccaa(ccaa)
                .provincias(provincias)
                .build();
    }
}
