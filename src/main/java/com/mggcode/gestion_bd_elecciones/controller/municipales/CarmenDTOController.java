package com.mggcode.gestion_bd_elecciones.controller.municipales;


import com.mggcode.gestion_bd_elecciones.DTO.municipales.CarmenDTO;
import com.mggcode.gestion_bd_elecciones.mapper.municipales.CarmenDTOMapper;
import com.mggcode.gestion_bd_elecciones.model.municipales.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/municipales/carmen")
public class CarmenDTOController {

    @Autowired
    private PartidoController parCon;

    @Autowired
    private CircunscripcionController cirCon;

    @Autowired
    private CircunscripcionPartidoService cpCon;

    @Autowired
    private CsvExportService csvExportService;


    //Este DTO trae los partidos de una circunscripción dada por código
    // ordenados del modo en que Carmen necesita para sus gráficos
    @GetMapping("/oficial/{codigo}")
    public ResponseEntity<CarmenDTO> getCarmenDTOOficial(@PathVariable("codigo") String cod1) {

        Circunscripcion circunscripcion = cirCon.findById(cod1).getBody();
        Circunscripcion espania = cirCon.findById("9900000").getBody();
        List<CircunscripcionPartido> cp = cpCon.findByIdCircunscripcionOficial(cod1).stream()
                //.filter(x -> x.getKey().getCircunscripcion().startsWith(cod1.substring(0, 2)))
                //.filter(x -> !x.getKey().getCircunscripcion().endsWith("00000"))
                //.filter(x -> x.getKey().getCircunscripcion().endsWith("000"))
                //.filter(x -> !x.getKey().getCircunscripcion().startsWith("99"))
                .filter(x -> x.getEscanos_hasta() > 0.0)
                //.sorted(Comparator.comparing(CircunscripcionPartido::getEscanos_hasta).reversed())
                .collect(Collectors.toList());

        List<Partido> partidos = new ArrayList<>();
        cp.forEach(x -> {
            partidos.add(parCon.findById(x.getKey().getPartido()).getBody());
        });
        CarmenDTOMapper mapper = new CarmenDTOMapper();
        CarmenDTO dto = mapper.toDTO(circunscripcion,espania, cp, partidos);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/sondeo/{codigo}")
    public ResponseEntity<CarmenDTO> getCarmenDTOSondeo(@PathVariable("codigo") String cod1) {

        Circunscripcion circunscripcion = cirCon.findById(cod1).getBody();
        Circunscripcion espania = cirCon.findById("9900000").getBody();
        List<CircunscripcionPartido> cp = cpCon.findByIdCircunscripcionSondeo(cod1).stream()
                //.filter(x -> x.getKey().getCircunscripcion().startsWith(cod1.substring(0, 2)))
                //.filter(x -> !x.getKey().getCircunscripcion().endsWith("00000"))
                //.filter(x -> x.getKey().getCircunscripcion().endsWith("000"))
                //.filter(x -> !x.getKey().getCircunscripcion().startsWith("99"))
                .filter(x -> x.getEscanos_hasta() > 0.0)
                //.sorted(Comparator.comparing(CircunscripcionPartido::getEscanos_hasta).reversed())
                .collect(Collectors.toList());

        List<Partido> partidos = new ArrayList<>();
        cp.forEach(x -> {
            partidos.add(parCon.findById(x.getKey().getPartido()).getBody());
        });
        CarmenDTOMapper mapper = new CarmenDTOMapper();
        CarmenDTO dto = mapper.toDTO(circunscripcion,espania, cp, partidos);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/oficial/{codigo}/csv")
    public void getCarmenDTOInCsvOficial(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CarmenDTO_" + cod1 + ".csv\"");
        CarmenDTO dto = getCarmenDTOOficial(cod1).getBody();
        csvExportService.writeCarmenDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "/sondeo/{codigo}/csv")
    public void getCarmenDTOInCsvSondeo(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CarmenDTO_" + cod1 + ".csv\"");
        CarmenDTO dto = getCarmenDTOSondeo(cod1).getBody();
        csvExportService.writeCarmenDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "/oficial/{codigo}/excel")
    public void getCarmenDTOInExcel(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=CarmenDTO_" + cod1 + ".xlsx");
        CarmenDTO dto = getCarmenDTOOficial(cod1).getBody();
        List<CarmenDTO> listado = new ArrayList<>();
        listado.add(dto);
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) listado, 4, servletResponse);
    }
    @RequestMapping(path = "/sondeo/{codigo}/excel")
    public void getCarmenDTOInExcelSondeo(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=CarmenDTO_" + cod1 + ".xlsx");
        CarmenDTO dto = getCarmenDTOSondeo(cod1).getBody();
        List<CarmenDTO> listado = new ArrayList<>();
        listado.add(dto);
        ExcelExportService excelExportService = new ExcelExportService();
        excelExportService.writeToExcel((RandomAccess) listado, 4, servletResponse);
    }
}
