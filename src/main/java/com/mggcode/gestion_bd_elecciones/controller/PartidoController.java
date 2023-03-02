package com.mggcode.gestion_bd_elecciones.controller;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.model.Partido;
import com.mggcode.gestion_bd_elecciones.service.CsvExportService;
import com.mggcode.gestion_bd_elecciones.service.ExcelExportService;
import com.mggcode.gestion_bd_elecciones.service.PartidoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @Autowired
    private CsvExportService csvExportService;

    @Autowired
    private ExcelExportService excelExportService;


    //RespondeEntity nos permite devolver el codigo http de estado
    @GetMapping
    public ResponseEntity<List<Partido>> findAll() {
        return new ResponseEntity<>(partidoService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/csv")
    public void findAllInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Partidos.csv\"");
        List<Partido> partidos = findAll().getBody();
        csvExportService.writePartidoToCsv(partidos, servletResponse.getWriter());
    }

    @RequestMapping(path = "/excel")
    public void findAllInExcel(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Partidos.xlsx\"");
        List<Partido> partidos = findAll().getBody();
        excelExportService.writePartidoToExcel(partidos, servletResponse);
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

    @RequestMapping(path = "/{codPartido}/csv")
    public void findByIdInCsv(@PathVariable("codPartido") String codPartido, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Partido" + codPartido + ".csv\"");
        Partido partido = findById(codPartido).getBody();
        List<Partido> partidos = new ArrayList<>();
        partidos.add(partido);
        csvExportService.writePartidoToCsv(partidos, servletResponse.getWriter());
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
