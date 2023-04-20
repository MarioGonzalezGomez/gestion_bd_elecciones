package com.mggcode.gestion_bd_elecciones.controller.autonomicas;


import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CarmenDTO;
import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CarmenDtoList;
import com.mggcode.gestion_bd_elecciones.mapper.autonomicas.CarmenDTOMapper;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Partido;
import com.mggcode.gestion_bd_elecciones.service.autonomicas.ACircunscripcionPartidoService;
import com.mggcode.gestion_bd_elecciones.service.autonomicas.ACsvExportService;
import com.mggcode.gestion_bd_elecciones.service.autonomicas.AExcelExportService;
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
@RequestMapping("/autonomicas/carmen")
public class ACarmenDTOController {

    @Autowired
    private APartidoController parCon;

    @Autowired
    private ACircunscripcionController cirCon;

    @Autowired
    private ACircunscripcionPartidoService cpCon;

    @Autowired
    private ACsvExportService csvExportService;


    //Este DTO trae los partidos de una circunscripción dada por código
    // ordenados del modo en que Carmen necesita para sus gráficos
    @GetMapping("/{codigo}")
    public ResponseEntity<CarmenDTO> getCarmenDTO(@PathVariable("codigo") String cod1) {

        Circunscripcion circunscripcion = cirCon.findById(cod1).getBody();

        List<CircunscripcionPartido> cp = cpCon.findByIdCircunscripcion(cod1).stream()
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
        CarmenDTO dto = mapper.toDTO(circunscripcion, cp, partidos);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<CarmenDtoList> findAll() {
        List<Circunscripcion> circunscripcionList = cirCon.findAll().getBody();

        List<CircunscripcionPartido> cp = cpCon.findAll().stream()
                //.filter(x -> x.getKey().getCircunscripcion().startsWith(cod1.substring(0, 2)))
                .filter(x -> !x.getKey().getCircunscripcion().endsWith("00000"))
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
        var dtoList = circunscripcionList.stream()
                .filter(c -> c.getCodigo().endsWith("00000") && !c.getCodigo().startsWith("99"))
                .map(c -> mapper.toDTO(c, cp, partidos)).toList();
        return new ResponseEntity<>(new CarmenDtoList(dtoList), HttpStatus.OK);

    }

    @RequestMapping(path = "/{codigo}/csv")
    public void getCarmenDTOInCsv(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=F_" + cod1 + ".csv\"");
        CarmenDTO dto = getCarmenDTO(cod1).getBody();
        csvExportService.writeCarmenDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "{codigo}/excel")
    public void getCarmenDTOInExcel(@PathVariable("codigo") String cod1, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=CarmenDTO_" + cod1 + ".xlsx");
        CarmenDTO dto = getCarmenDTO(cod1).getBody();
        List<CarmenDTO> listado = new ArrayList<>();
        listado.add(dto);
        AExcelExportService excelExportService = new AExcelExportService();
        excelExportService.writeToExcel((RandomAccess) listado, 4, servletResponse);
    }
}
