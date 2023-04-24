package com.mggcode.gestion_bd_elecciones.service.municipales;


import com.mggcode.gestion_bd_elecciones.logic.municipales.CircunscripcionPartidoOficial;
import com.mggcode.gestion_bd_elecciones.logic.municipales.CircunscripcionPartidoSondeo;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;

import com.mggcode.gestion_bd_elecciones.model.municipales.Key;
import com.mggcode.gestion_bd_elecciones.repository.municipales.CircunscripcionPartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CircunscripcionPartidoService {
    @Autowired
    private CircunscripcionPartidoRepository circunscripcionPartidoRepository;


    public CircunscripcionPartido create(CircunscripcionPartido circunscripcionPartido) {
        return circunscripcionPartidoRepository.save(circunscripcionPartido);
    }


    public CircunscripcionPartido update(CircunscripcionPartido circunscripcionPartido) {
        return circunscripcionPartidoRepository.save(circunscripcionPartido);
    }


    public CircunscripcionPartido findById(Key id) {
        Optional<CircunscripcionPartido> CircunscripcionPartidoOptional = circunscripcionPartidoRepository.findById(id);
        return CircunscripcionPartidoOptional.orElse(null);
    }

    public List<CircunscripcionPartido> findByIdCircunscripcionOficial(String codCircunscripcion) {
        return circunscripcionPartidoRepository.findByKey_Circunscripcion(codCircunscripcion)
                .stream().filter(x -> x.getEscanos_hasta() > 0)
                .sorted(new CircunscripcionPartidoOficial().reversed()).collect(Collectors.toList());
    }

    public List<CircunscripcionPartido> findByIdCircunscripcionSondeo(String codCircunscripcion) {
        return circunscripcionPartidoRepository.findByKey_Circunscripcion(codCircunscripcion)
                .stream().filter(x -> x.getEscanos_hasta() > 0)
                .sorted(new CircunscripcionPartidoSondeo().reversed()).collect(Collectors.toList());
    }

    public List<CircunscripcionPartido> findByIdPartido(String codPartido) {
        List<CircunscripcionPartido> lista = circunscripcionPartidoRepository.findByKey_Partido(codPartido);
        return lista;
    }


    public List<CircunscripcionPartido> findAll() {
        return circunscripcionPartidoRepository.findAll();
    }


    public void delete(Key id) {
        circunscripcionPartidoRepository.deleteById(id);
    }

}


