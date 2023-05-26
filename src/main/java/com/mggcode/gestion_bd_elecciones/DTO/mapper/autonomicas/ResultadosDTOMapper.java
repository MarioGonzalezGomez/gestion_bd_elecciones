package com.mggcode.gestion_bd_elecciones.DTO.mapper.autonomicas;


import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CircunscripcionResultadosDTO;
import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.ProvinciaResultadosDTO;
import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.ResultadosDTO;
import com.mggcode.gestion_bd_elecciones.logic.autonomicas.ComparadorCombinado;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;

import java.util.ArrayList;
import java.util.List;


public class ResultadosDTOMapper {

    public ResultadosDTO toDTO(Circunscripcion c, List<CircunscripcionPartido> cps, List<String> nombresProvincias, List<String> nombresGanadores) {
        CircunscripcionResultadosDTOMapper mapperCir = new CircunscripcionResultadosDTOMapper();
        //cps.sort(new ComparadorCombinado());
        ProvinciaResultadosDTOMapper mapperPro = new ProvinciaResultadosDTOMapper();
        CircunscripcionResultadosDTO ccaa = mapperCir.toDTO(c, cps.size());

        List<ProvinciaResultadosDTO> provincias = new ArrayList<>();
        cps.forEach(cp -> {
            ProvinciaResultadosDTO provincia = mapperPro.toDTO(
                    cp, nombresProvincias.get(cps.indexOf(cp)), nombresGanadores.get(cps.indexOf(cp)));
            provincias.add(provincia);
        });

        return ResultadosDTO.builder()
                .ccaa(ccaa)
                .provincias(provincias)
                .build();
    }
}
