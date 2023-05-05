package com.mggcode.gestion_bd_elecciones.controller.municipales;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.model.municipales.Literal;
import com.mggcode.gestion_bd_elecciones.service.municipales.LiteralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipales/literales")
public class LiteralController {

    @Autowired
    private LiteralService literalService;

    @GetMapping
    public ResponseEntity<List<Literal>> findAll() {
        return new ResponseEntity<>(literalService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Literal> findById(@PathVariable("codigo") String codigo) {
        Literal literal = literalService.findById(codigo);
        if (literal == null) {
            throw new ModelNotFoundException("Literal no encontrado");
        }
        return new ResponseEntity<>(literal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Literal> create(@RequestBody Literal literal) {
        return new ResponseEntity<>(literalService.create(literal), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Literal literal) {
        literalService.update(literal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Object> delete(@PathVariable("codigo") String codigo) {
        Literal literal = literalService.findById(codigo);
        if (literal == null) {
            throw new ModelNotFoundException("El literal que desea eliminar no existe");
        }
        literalService.delete(codigo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
