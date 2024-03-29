package com.mggcode.gestion_bd_elecciones.controller.municipales;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.service.municipales.CircunscripcionService;
import com.mggcode.gestion_bd_elecciones.service.municipales.CsvExportService;
import com.mggcode.gestion_bd_elecciones.service.municipales.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

@RestController
@RequestMapping("/municipales/circunscripciones")
public class CircunscripcionController {

    @Autowired
    private CircunscripcionService circunscripcionService;

    @Autowired
    private CsvExportService csvExportService;

    //RespondeEntity nos permite devolver el codigo http de estado
    @GetMapping
    public ResponseEntity<List<Circunscripcion>> findAll() {
        return new ResponseEntity<>(circunscripcionService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/filtrada")
    public ResponseEntity<List<Circunscripcion>> filtradasPorMostrar() {
        List<Circunscripcion> filtradas = circunscripcionService.findAll().stream().filter(c -> c.getMostrar() == 1).toList();
        return new ResponseEntity<>(filtradas, HttpStatus.OK);
    }

    @RequestMapping(path = "/csv")
    public void findAllInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Circunscripciones.csv\"");
        List<Circunscripcion> circunscripciones = findAll().getBody();
        csvExportService.writeCircunscripcionToCsv(circunscripciones, servletResponse.getWriter());
    }

    @RequestMapping(path = "/excel")
    public void findAllInExcel(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=Circunscripciones.xlsx");
        List<Circunscripcion> circunscripciones = findAll().getBody();
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) circunscripciones, 2, servletResponse);
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

    @RequestMapping(path = "/{codigo}/csv")
    public void findByIdInCsv(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Circunscripcion" + cod1 + ".csv\"");
        Circunscripcion circunscripcion = findById(cod1).getBody();
        List<Circunscripcion> circuns = new ArrayList<>();
        circuns.add(circunscripcion);
        csvExportService.writeCircunscripcionToCsv(circuns, servletResponse.getWriter());
    }

    @RequestMapping(path = "/{codigo}/excel")
    public void findByIdInExcel(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=Circunscripcion" + cod1 + ".xlsx");
        Circunscripcion circunscripcion = findById(cod1).getBody();
        List<Circunscripcion> circunscripciones = new ArrayList<>();
        circunscripciones.add(circunscripcion);
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) circunscripciones, 2, servletResponse);
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
