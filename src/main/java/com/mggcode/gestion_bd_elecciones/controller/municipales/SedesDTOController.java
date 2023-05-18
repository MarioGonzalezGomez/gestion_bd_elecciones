package com.mggcode.gestion_bd_elecciones.controller.municipales;


import com.mggcode.gestion_bd_elecciones.DTO.municipales.SedesDTO;
import com.mggcode.gestion_bd_elecciones.DTO.mapper.municipales.SedesDTOMapper;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.municipales.Key;
import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;
import com.mggcode.gestion_bd_elecciones.service.municipales.CircunscripcionPartidoService;
import com.mggcode.gestion_bd_elecciones.service.municipales.CsvExportService;
import com.mggcode.gestion_bd_elecciones.service.municipales.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

@RestController
@RequestMapping("/municipales/sedes")
public class SedesDTOController {

    @Autowired
    private PartidoController parCon;

    @Autowired
    private CircunscripcionPartidoService cpCon;

    @Autowired
    private CsvExportService csvExportService;

    @GetMapping("/{partido}")
    public ResponseEntity<SedesDTO> findById(@PathVariable("partido") String codPartido) {
        Partido p = parCon.findById(codPartido).getBody();
        Key key = new Key();
        key.setCircunscripcion("9900000");
        key.setPartido(codPartido);
        CircunscripcionPartido cp = cpCon.findById(key);
        SedesDTOMapper mapper = new SedesDTOMapper();
        SedesDTO dto = mapper.toDTO(cp, p);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "{partido}/csv")
    public void getSedesDTOInCsv(@PathVariable("partido") String partido, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=F_sedes.csv");
        SedesDTO dto = findById(partido).getBody();
        csvExportService.writeSedesDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "{partido}/excel")
    public void getSedesDTOInExcel(@PathVariable("partido") String partido, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=F_sedes.xlsx");
        SedesDTO dto = findById(partido).getBody();
        List<SedesDTO> listado = new ArrayList<>();
        listado.add(dto);
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) listado, 5, servletResponse);
    }
}
