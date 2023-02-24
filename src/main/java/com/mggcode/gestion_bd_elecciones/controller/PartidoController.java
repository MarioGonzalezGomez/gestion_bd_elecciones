package com.mggcode.gestion_bd_elecciones.controller;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.model.Partido;
import com.mggcode.gestion_bd_elecciones.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;


    //RespondeEntity nos permite devolver el codigo http de estado
    @GetMapping
    public ResponseEntity<List<Partido>> findAll() {
        return new ResponseEntity<>(partidoService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Partido> create(@RequestBody Partido partido) {
        return new ResponseEntity<>(partidoService.create(partido), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Partido partido) {
        partidoService.update(partido);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{codPartido}")
    public ResponseEntity<Partido> findById(@PathVariable("codPartido") String codPartido) {
        Partido partido = partidoService.findById(codPartido);
        if (partido == null) {
            throw new ModelNotFoundException("Partido no encontrado");
        }
        return new ResponseEntity<>(partido, HttpStatus.OK);
    }

    @DeleteMapping("/{codPartido}")
    public ResponseEntity<Object> delete(@PathVariable("codPartido") String codPartido) {
        Partido partido = partidoService.findById(codPartido);
        if (partido == null) {
            throw new ModelNotFoundException("El partido que desea eliminar no existe");
        }
        partidoService.delete(codPartido);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
