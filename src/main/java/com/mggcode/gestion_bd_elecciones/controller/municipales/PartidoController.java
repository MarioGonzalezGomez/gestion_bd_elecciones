package com.mggcode.gestion_bd_elecciones.controller.municipales;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;
import com.mggcode.gestion_bd_elecciones.service.municipales.CsvExportService;
import com.mggcode.gestion_bd_elecciones.service.municipales.ExcelExportService;
import com.mggcode.gestion_bd_elecciones.service.municipales.PartidoService;
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
@RequestMapping("/municipales/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @Autowired
    private CsvExportService csvExportService;

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

    @RequestMapping(path = "/filtrado/csv")
    public void findFiltradoInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Partidos.csv\"");
        List<Partido> partidos = findAll().getBody().stream().filter(partido -> !partido.getCodigo().equals(partido.getCodigoPadre())).toList();
        csvExportService.writePartidoToCsv(partidos, servletResponse.getWriter());
    }

    //Imagino que pesa demasiado el proceso y no es capaz de sacar el excel
    @RequestMapping(path = "/excel")
    public void findAllInExcel(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=Partidos.xlsx");
        List<Partido> partidos = findAll().getBody();
        partidos = partidos.subList(0, 500);
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) partidos, 1, servletResponse);
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

    @RequestMapping(path = "/{codPartido}/excel")
    public void findByIdInExcel(@PathVariable("codPartido") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=Partido" + cod1 + ".xlsx");
        Partido partido = findById(cod1).getBody();
        List<Partido> partidos = new ArrayList<>();
        partidos.add(partido);
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) partidos, 1, servletResponse);
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
