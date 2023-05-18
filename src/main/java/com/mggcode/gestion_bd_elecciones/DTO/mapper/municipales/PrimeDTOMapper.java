package com.mggcode.gestion_bd_elecciones.DTO.mapper.municipales;

import com.mggcode.gestion_bd_elecciones.DTO.municipales.CpPrimeDTO;
import com.mggcode.gestion_bd_elecciones.DTO.municipales.PrimeDTO;
import com.mggcode.gestion_bd_elecciones.logic.LecturaFicheroColores;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimeDTOMapper {


    public PrimeDTO toDTO(Circunscripcion c, List<CircunscripcionPartido> cp, List<Partido> p) {
        Map<String, String> colores = crearMapaPartidos();
        List<CpPrimeDTO> primes = new ArrayList<>();
        cp.forEach(x -> {
            Partido pTemp = p.stream().filter(y -> y.getCodigo().equals(x.getKey().getPartido())).toList().get(0);
            String color = colores.getOrDefault(pTemp.getCodigo(), "#000000");
            CpPrimeDTO cpTemP = CpPrimeDTO.builder()
                    .codigoPartido(pTemp.getCodigo())
                    .escanios(x.getEscanos_hasta())
                    .escaniosHistorico(x.getEscanos_hasta_hist())
                    .color(color)
                    .build();
            primes.add(cpTemP);
        });

        return PrimeDTO.builder()
                .nombreCircunscripcion(c.getNombreCircunscripcion())
                .codigoCircunscripcion(c.getCodigo())
                .escaniosTotales(c.getEscanios())
                .escrutado(c.getEscrutado())
                .cps(primes)
                .build();
    }

    private Map<String, String> crearMapaPartidos() {
        LecturaFicheroColores lfc = new LecturaFicheroColores();
        ArrayList<String[]> partidos = lfc.leerFicheroColores();
        Map<String, String> mapaPartidos = new HashMap<>();
        for (String[] partido : partidos) {
            mapaPartidos.put(partido[0], partido[4]);
        }
        return mapaPartidos;
    }
}
