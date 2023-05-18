package com.mggcode.gestion_bd_elecciones.controller.municipales;

import com.mggcode.gestion_bd_elecciones.DTO.municipales.PrimeDTO;
import com.mggcode.gestion_bd_elecciones.DTO.mapper.municipales.PrimeDTOMapper;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;
import com.mggcode.gestion_bd_elecciones.service.municipales.CircunscripcionPartidoService;
import com.mggcode.gestion_bd_elecciones.service.municipales.CircunscripcionService;
import com.mggcode.gestion_bd_elecciones.service.municipales.ExcelExportService;
import com.mggcode.gestion_bd_elecciones.service.municipales.PartidoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

@RestController
@RequestMapping("/municipales/prime")
public class PrimeDTOController {

    @Autowired
    private PartidoService parCon;

    @Autowired
    private CircunscripcionService cirCon;

    @Autowired
    private CircunscripcionPartidoService cpCon;

    @GetMapping()
    public ResponseEntity<List<PrimeDTO>> findAll() {
        PrimeDTOMapper mapper = new PrimeDTOMapper();
        List<PrimeDTO> listado = new ArrayList<>();
        List<Circunscripcion> circunscripciones = cirCon.findAll().stream().filter(cir -> cir.getCodigo().endsWith("00000")).filter(cir -> !cir.getCodigo().startsWith("99")).toList();
        List<Partido> partidos = parCon.findAll();

        circunscripciones.forEach(cir -> {
            List<CircunscripcionPartido> cps = cpCon.findByIdCircunscripcionOficial(cir.getCodigo());
            PrimeDTO dto = mapper.toDTO(cir, cps, partidos);
            listado.add(dto);
        });

        return new ResponseEntity<>(listado, HttpStatus.OK);
    }

    @RequestMapping(path = "/excel")
    public void findAllInExcel(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=P_Partidos.xlsx");
        List<PrimeDTO> primes = findAll().getBody();
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) primes, 6, servletResponse);
    }
}
