package com.mggcode.gestion_bd_elecciones.controller;

import com.mggcode.gestion_bd_elecciones.exception.ModelNotFoundException;
import com.mggcode.gestion_bd_elecciones.logic.Comparador;
import com.mggcode.gestion_bd_elecciones.model.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.Key;
import com.mggcode.gestion_bd_elecciones.service.CircunscripcionPartidoService;
import com.mggcode.gestion_bd_elecciones.service.CsvExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cp")
public class CircunscripcionPartidoController {

    @Autowired
    private CircunscripcionPartidoService circunscripcionPartidoService;

    @Autowired
    private CsvExportService csvExportService;

    //RespondeEntity nos permite devolver el codigo http de estado
    @GetMapping
    public ResponseEntity<List<CircunscripcionPartido>> findAll() {
        return new ResponseEntity<>(circunscripcionPartidoService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/csv")
    public void findAllInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"Circunscripcion_Partido.csv\"");
        List<CircunscripcionPartido> cp = findAll().getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
    }


    //Devuelve solo el partido mayoritario en España por autonomía
    @GetMapping("/mayorias/autonomias")
    public ResponseEntity<List<CircunscripcionPartido>> masVotadosPorAutonomia() {
        List<CircunscripcionPartido> mayoritarios = circunscripcionPartidoService.findAll()
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("00000"))
                .filter(x -> !x.getKey().getCircunscripcion().startsWith("99"))
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

    @RequestMapping(path = "/mayorias/autonomias/csv")
    public void masVotadosPorAutonomiaInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"CP_MasVotadoPorAutonomia.csv\"");
        List<CircunscripcionPartido> cp = masVotadosPorAutonomia().getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
    }

    //Devuelve solo el partido mayoritario en España por provincia
    @GetMapping("/mayorias/provincias")
    public ResponseEntity<List<CircunscripcionPartido>> masVotadosPorProvincia() {
        List<CircunscripcionPartido> mayoritarios = circunscripcionPartidoService.findAll()
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("000"))
                .filter(x -> !x.getKey().getCircunscripcion().startsWith("99"))
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

    @RequestMapping(path = "/mayorias/provincias/csv")
    public void masVotadosPorProvinciaInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"CP_MasVotadoPorProvincia.csv\"");
        List<CircunscripcionPartido> cp = masVotadosPorProvincia().getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
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

    @RequestMapping(path = "/circunscripcion/{codigo}/csv")
    public void findByIdCircunscripcionInCsv(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CP_DatosCircunscripcion_" + cod1 + ".csv\"");
        List<CircunscripcionPartido> cp = findByIdCircunscripcion(cod1).getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
    }

    //Obtener todos los datos de un partido en una autonomía en específico
    @GetMapping("/circunscripcion/{codigo1}/partido/{codigo2}")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdPartidoAutonomicoPorProvincias(@PathVariable("codigo1") String cod1, @PathVariable("codigo2") String cod2) {
        String autonomia = cod1.substring(0, 2);
        List<CircunscripcionPartido> listaPartido = circunscripcionPartidoService.findByIdPartido(cod2).stream().filter(x -> x.getKey().getCircunscripcion().startsWith(autonomia)
        ).filter(x -> x.getKey().getCircunscripcion().endsWith("000")).filter(x -> !x.getKey().getCircunscripcion().startsWith("99")).collect(Collectors.toList());
        return new ResponseEntity<>(listaPartido, HttpStatus.OK);
    }

    @RequestMapping(path = "/circunscripcion/{codigo1}/partido/{codigo2}/csv")
    public void findByIdPartidoAutonomicoPorProvinciasInCsv(@PathVariable("codigo1") String cod1, @PathVariable("codigo2") String cod2, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CP_" + cod1 + "_" + cod2 + ".csv\"");
        List<CircunscripcionPartido> cp = findByIdPartidoAutonomicoPorProvincias(cod1, cod2).getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
    }

    //Obtener datos de un partido en España, por autonomías
    @GetMapping("/partido/{codigo}/autonomias/orderByCodAuto")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdPartidoAutonomiasCod(@PathVariable("codigo") String cod1) {
        List<CircunscripcionPartido> listaPartido = circunscripcionPartidoService.findByIdPartido(cod1)
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("00000")).filter(x -> !x.getKey().getCircunscripcion().startsWith("99"))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaPartido, HttpStatus.OK);
    }

    @RequestMapping(path = "/partido/{codigo}/autonomias/orderByCodAuto/csv")
    public void findByIdPartidoAutonomiasCodInCsv(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CP_Partido_" + cod1 + "_Autonomias_OrderCodAutonomia.csv\"");
        List<CircunscripcionPartido> cp = findByIdPartidoAutonomiasCod(cod1).getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
    }

    @GetMapping("/partido/{codigo}/autonomias/orderByEscanios")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdPartidoAutonomiasEscanios(@PathVariable("codigo") String cod1) {
        List<CircunscripcionPartido> listaPartido = circunscripcionPartidoService.findByIdPartido(cod1)
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("00000")).filter(x -> !x.getKey().getCircunscripcion().startsWith("99"))
                .sorted(Comparator.comparing(CircunscripcionPartido::getEscanos_hasta).reversed())
                .collect(Collectors.toList());
        return new ResponseEntity<>(listaPartido, HttpStatus.OK);
    }

    @RequestMapping(path = "/partido/{codigo}/autonomias/orderByEscanios/csv")
    public void findByIdPartidoAutonomiasEscaniosInCsv(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CP_Partido_" + cod1 + "_Autonomias_OrderPorEscanios.csv\"");
        List<CircunscripcionPartido> cp = findByIdPartidoAutonomiasEscanios(cod1).getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
    }

    //Obtener datos de un partido en España, por provincias
    @GetMapping("/partido/{codigo}/provincias")
    public ResponseEntity<List<CircunscripcionPartido>> findByIdPartidoProvincias(@PathVariable("codigo") String cod1) {
        List<CircunscripcionPartido> listaPartido = circunscripcionPartidoService.findByIdPartido(cod1)
                .stream().filter(x -> x.getKey().getCircunscripcion().endsWith("000")).filter(x -> !x.getKey().getCircunscripcion().startsWith("99")).collect(Collectors.toList());
        return new ResponseEntity<>(listaPartido, HttpStatus.OK);
    }

    @RequestMapping(path = "/partido/{codigo}/provincias/csv")
    public void findByIdPartidoProvinciasInCsv(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CP_Partido_" + cod1 + "_Provincias.csv\"");
        List<CircunscripcionPartido> cp = findByIdPartidoProvincias(cod1).getBody();
        csvExportService.writeCPToCsv(cp, servletResponse.getWriter());
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
