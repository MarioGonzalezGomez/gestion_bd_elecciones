package com.mggcode.gestion_bd_elecciones.controller.autonomicas;

import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.ResultadosDTO;
import com.mggcode.gestion_bd_elecciones.DTO.mapper.autonomicas.ResultadosDTOMapper;
import com.mggcode.gestion_bd_elecciones.logic.autonomicas.ComparadorCombinado;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.service.autonomicas.ACsvExportService;
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

@RestController
@RequestMapping("/autonomicas/resultados")
public class AResultadosController {

    @Autowired
    private APartidoController parCon;

    @Autowired
    private ACircunscripcionController cirCon;

    @Autowired
    private ACircunscripcionPartidoController cpCon;

    @Autowired
    private ACsvExportService csvExportService;

    @GetMapping("/sondeo/{codigo}")
    public ResponseEntity<ResultadosDTO> getResultadosDTOSondeo(@PathVariable("codigo") String cod) {
        Circunscripcion circunscripcion = cirCon.findById(cod).getBody();
        List<CircunscripcionPartido> cps = cpCon.masVotadosAutonomicoPorProvinciaSondeo(cod).getBody();
        cps.sort(new ComparadorCombinado().reversed());
        List<String> nombreProvincias = new ArrayList<>();
        List<String> nombreGanadores = new ArrayList<>();
        cps.forEach(cp -> {
            String ganador = parCon.findById(cp.getKey().getPartido()).getBody().getSiglas();
            String provincia = cirCon.findById(cp.getKey().getCircunscripcion()).getBody().getNombreCircunscripcion();
            nombreGanadores.add(ganador);
            nombreProvincias.add(provincia);
        });
        ResultadosDTOMapper mapper = new ResultadosDTOMapper();
        ResultadosDTO resultados = mapper.toDTO(circunscripcion, cps, nombreProvincias, nombreGanadores);
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    @GetMapping("/oficial/{codigo}")
    public ResponseEntity<ResultadosDTO> getResultadosDTOOficial(@PathVariable("codigo") String cod) {
        Circunscripcion circunscripcion = cirCon.findById(cod).getBody();
        List<CircunscripcionPartido> cps = cpCon.masVotadosAutonomicoPorProvinciaOficial(cod).getBody();
        cps.sort(new ComparadorCombinado().reversed());
        List<String> nombreProvincias = new ArrayList<>();
        List<String> nombreGanadores = new ArrayList<>();
        cps.forEach(cp -> {
            String ganador = parCon.findById(cp.getKey().getPartido()).getBody().getSiglas();
            String provincia = cirCon.findById(cp.getKey().getCircunscripcion()).getBody().getNombreCircunscripcion();
            nombreGanadores.add(ganador);
            nombreProvincias.add(provincia);
        });
        ResultadosDTOMapper mapper = new ResultadosDTOMapper();
        ResultadosDTO resultados = mapper.toDTO(circunscripcion, cps, nombreProvincias, nombreGanadores);
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    @RequestMapping(path = "/oficial/{codigo}/csv")
    public void getResultadosDTOInCsvOficial(@PathVariable("codigo") String cod, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"ResultadosDTO_" + cod + ".csv\"");
       ResultadosDTO dto = getResultadosDTOOficial(cod).getBody();
        csvExportService.writeResultadosDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "/sondeo/{codigo}/csv")
    public void getResultadosDTOInCsvSondeo(@PathVariable("codigo") String cod, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"ResultadosDTO_" + cod + ".csv\"");
        ResultadosDTO dto = getResultadosDTOSondeo(cod).getBody();
        csvExportService.writeResultadosDTOToCsv(dto, servletResponse.getWriter());
    }


}
