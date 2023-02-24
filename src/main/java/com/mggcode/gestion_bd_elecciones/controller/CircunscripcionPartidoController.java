package com.mggcode.gestion_bd_elecciones.controller;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.logic.Comparador;
import com.mggcode.gestion_bd_elecciones.model.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.Key;
import com.mggcode.gestion_bd_elecciones.service.CircunscripcionPartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cp")
public class CircunscripcionPartidoController {

    @Autowired
    private CircunscripcionPartidoService circunscripcionPartidoService;

    //RespondeEntity nos permite devolver el codigo http de estado
    @GetMapping
    public ResponseEntity<List<CircunscripcionPartido>> findAll() {
        return new ResponseEntity<>(circunscripcionPartidoService.findAll(), HttpStatus.OK);
    }

    //Devuelve solo el partido mayoritario en España por autonomía
    @GetMapping("/mayorias/autonomias")
    public ResponseEntity<List<CircunscripcionPartido>> masVotadosPorAutonomia() {
        List<CircunscripcionPartido> mayoritarios = circunscripcionPartidoService.findAll()
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("00000"))
                .sorted(Comparator.comparing(CircunscripcionPartido::getEscanos_hasta).reversed())
                .collect(Collectors.toList());
        List<String> CCAA = new ArrayList<>();
        List<CircunscripcionPartido> filtrada = new ArrayList<>();
        for (CircunscripcionPartido cp : mayoritarios) {
            String ccaa = cp.getKey().getCircunscripcion().substring(0, 2);
            if (!CCAA.contains(ccaa)) {
                filtrada.add(cp);
                CCAA.add(ccaa);
            }
        }
        filtrada.sort(new Comparador());
        return new ResponseEntity<>(filtrada, HttpStatus.OK);
    }

    //Devuelve solo el partido mayoritario en España por provincia
    @GetMapping("/mayorias/provincias")
    public ResponseEntity<List<CircunscripcionPartido>> masVotadosPorProvincia() {
        List<CircunscripcionPartido> mayoritarios = circunscripcionPartidoService.findAll()
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("000"))
                .sorted(Comparator.comparing(CircunscripcionPartido::getEscanos_hasta).reversed())
                .collect(Collectors.toList());
        List<String> provincia = new ArrayList<>();
        List<CircunscripcionPartido> filtrada = new ArrayList<>();
        for (CircunscripcionPartido cp : mayoritarios) {
            String ccaa = cp.getKey().getCircunscripcion().substring(2, 4);
            if (!provincia.contains(ccaa)) {
                filtrada.add(cp);
                provincia.add(ccaa);
            }
        }
        filtrada.remove(0);
        filtrada.sort(new Comparador());
        return new ResponseEntity<>(filtrada, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CircunscripcionPartido> create(@RequestBody CircunscripcionPartido circunscripcionPartido) {
        return new ResponseEntity<>(circunscripcionPartidoService.create(circunscripcionPartido), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody CircunscripcionPartido circunscripcionPartido) {
        circunscripcionPartidoService.update(circunscripcionPartido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{codigo1}/{codigo2}")
    public ResponseEntity<CircunscripcionPartido> findById(@PathVariable("codigo1") String cod1, @PathVariable("codigo2") String cod2) {
        Key key = new Key(cod1, cod2);
        CircunscripcionPartido circunscripcionPartido = circunscripcionPartidoService.findById(key);
        if (circunscripcionPartido == null) {
            throw new ModelNotFoundException("CircunscripcionPartido no encontrado");
        }
        return new ResponseEntity<>(circunscripcionPartido, HttpStatus.OK);
    }

    @GetMapping("/circunscripcion/{codigo}")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdCircunscripcion(@PathVariable("codigo") String cod1) {
        return new ResponseEntity<>(circunscripcionPartidoService.findByIdCircunscripcion(cod1), HttpStatus.OK);
    }

    //Obtener todos los datos de un partido en una autonomía en específico
    @GetMapping("/circunscripcion/{codigo1}/partido/{codigo2}")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdPartidoAutonomicoPorProvincias(@PathVariable("codigo1") String cod1, @PathVariable("codigo2") String cod2) {
        String autonomia = cod1.substring(0, 2);
        List<CircunscripcionPartido> listaPartido = circunscripcionPartidoService.findByIdPartido(cod2).stream().filter(x -> x.getKey().getCircunscripcion().startsWith(autonomia)
        ).filter(x -> x.getKey().getCircunscripcion().endsWith("000")).collect(Collectors.toList());
        return new ResponseEntity<>(listaPartido, HttpStatus.OK);
    }

    //Obtener datos de un partido en España, por autonomías
    @GetMapping("/partido/{codigo}/autonomias")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdPartidoAutonomias(@PathVariable("codigo") String cod1) {
        List<CircunscripcionPartido> listaPartido = circunscripcionPartidoService.findByIdPartido(cod1)
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("00000")).collect(Collectors.toList());
        return new ResponseEntity<>(listaPartido, HttpStatus.OK);
    }

    //Obtener datos de un partido en España, por provincias
    @GetMapping("/partido/{codigo}/provincias")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdPartidoProvincias(@PathVariable("codigo") String cod1) {
        List<CircunscripcionPartido> listaPartido = circunscripcionPartidoService.findByIdPartido(cod1)
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("000")).collect(Collectors.toList());
        return new ResponseEntity<>(listaPartido, HttpStatus.OK);
    }

    @DeleteMapping("/{codigo1}/{codigo2}")
    public ResponseEntity<Object> delete(@PathVariable("codigo1") String cod1, @PathVariable("codigo2") String cod2) {
        Key key = new Key(cod1, cod2);
        CircunscripcionPartido circunscripcionPartido = circunscripcionPartidoService.findById(key);
        if (circunscripcionPartido == null) {
            throw new ModelNotFoundException("El circunscripcion-partido que desea eliminar no existe");
        }
        circunscripcionPartidoService.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
