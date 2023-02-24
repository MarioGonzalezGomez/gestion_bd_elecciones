package com.mggcode.gestion_bd_elecciones.controller;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.model.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.service.CircunscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/circunscripciones")
public class CircunscripcionController {

    @Autowired
    private CircunscripcionService circunscripcionService;

    //RespondeEntity nos permite devolver el codigo http de estado
    @GetMapping
    public ResponseEntity<List<Circunscripcion>> findAll() {
        return new ResponseEntity<>(circunscripcionService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Circunscripcion> create(@RequestBody Circunscripcion circunscripcion) {
        return new ResponseEntity<>(circunscripcionService.create(circunscripcion), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Circunscripcion circunscripcion) {
        circunscripcionService.update(circunscripcion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Circunscripcion> findById(@PathVariable("codigo") String codcircunscripcion) {
        Circunscripcion circunscripcion = circunscripcionService.findById(codcircunscripcion);
        if (circunscripcion == null) {
            throw new ModelNotFoundException("Circunscripcion no encontrado");
        }
        return new ResponseEntity<>(circunscripcion, HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Object> delete(@PathVariable("codigo") String codcircunscripcion) {
        Circunscripcion circunscripcion = circunscripcionService.findById(codcircunscripcion);
        if (circunscripcion == null) {
            throw new ModelNotFoundException("El circunscripcion que desea eliminar no existe");
        }
        circunscripcionService.delete(codcircunscripcion);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
