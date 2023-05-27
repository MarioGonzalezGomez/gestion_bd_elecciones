package com.mggcode.gestion_bd_elecciones.controller.autonomicas;


import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CarmenDTO;
import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CarmenDtoList;
import com.mggcode.gestion_bd_elecciones.DTO.mapper.autonomicas.CarmenDTOMapper;
import com.mggcode.gestion_bd_elecciones.logic.autonomicas.CircunscripcionPartidoOficial;
import com.mggcode.gestion_bd_elecciones.logic.autonomicas.CircunscripcionPartidoSondeo;
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
    @GetMapping("/oficial/{codigo}/{avance}")
    public ResponseEntity<CarmenDTO> getCarmenDTOOficial(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance) {

        Circunscripcion circunscripcion = cirCon.findById(cod1).getBody();
        Circunscripcion espania = cirCon.findById("9900000").getBody();
        List<CircunscripcionPartido> cp = cpCon.findByIdCircunscripcionOficial(cod1).stream()
                .filter(x -> x.getEscanos_hasta() > 0)
                .sorted(new CircunscripcionPartidoOficial().reversed())
                .collect(Collectors.toList());
        List<Partido> partidos = new ArrayList<>();
        cp.forEach(x -> {
            partidos.add(parCon.findById(x.getKey().getPartido()).getBody());
        });
        CarmenDTOMapper mapper = new CarmenDTOMapper(avance);
        CarmenDTO dto = mapper.toDTO(circunscripcion, espania, cp, partidos);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/sondeo/{codigo}/{avance}")
    public ResponseEntity<CarmenDTO> getCarmenDTOSondeo(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance) {

        Circunscripcion circunscripcion = cirCon.findById(cod1).getBody();
        Circunscripcion espania = cirCon.findById("9900000").getBody();
        List<CircunscripcionPartido> cp = cpCon.findByIdCircunscripcionSondeo(cod1).stream()
                .filter(x -> x.getEscanos_hasta_sondeo() > 0)
                .sorted(new CircunscripcionPartidoSondeo().reversed())
                .collect(Collectors.toList());

        List<Partido> partidos = new ArrayList<>();
        cp.forEach(x -> partidos.add(parCon.findById(x.getKey().getPartido()).getBody()));
        CarmenDTOMapper mapper = new CarmenDTOMapper(avance);
        CarmenDTO dto = mapper.toDTO(circunscripcion, espania, cp, partidos);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<CarmenDtoList> findAll() {
        List<Circunscripcion> circunscripcionList = cirCon.findAll().getBody();
        Circunscripcion espania = cirCon.findById("9900000").getBody();
        List<CircunscripcionPartido> cp = cpCon.findAll().stream()
                .filter(x -> !x.getKey().getCircunscripcion().endsWith("00000"))
                .filter(x -> x.getEscanos_hasta() > 0.0)
                .sorted(new CircunscripcionPartidoOficial().reversed())
                .collect(Collectors.toList());

        List<Partido> partidos = new ArrayList<>();
        cp.forEach(x -> {
            partidos.add(parCon.findById(x.getKey().getPartido()).getBody());
        });
        CarmenDTOMapper mapper = new CarmenDTOMapper();
        var dtoList = circunscripcionList.stream()
                .filter(c -> c.getCodigo().endsWith("00000"))
                .map(c -> mapper.toDTO(c, espania, cp, partidos)).toList();
        return new ResponseEntity<>(new CarmenDtoList(dtoList), HttpStatus.OK);

    }

    @RequestMapping(path = "/oficial/{codigo}/{avance}/csv")
    public void getCarmenDTOInCsvOficial(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CarmenDTO_" + cod1 + ".csv\"");
        CarmenDTO dto = getCarmenDTOOficial(cod1, avance).getBody();
        csvExportService.writeCarmenDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "/sondeo/{codigo}/{avance}/csv")
    public void getCarmenDTOInCsvSondeo(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CarmenDTO_" + cod1 + ".csv\"");
        CarmenDTO dto = getCarmenDTOSondeo(cod1, avance).getBody();
        csvExportService.writeCarmenDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "/sondeo/especial/{codigo}/{avance}/csv")
    public void getCarmenDTOInCsvSondeoEspecial(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; " + "filename=\"CarmenDTO_" + cod1 + ".csv\"");
        CarmenDTO dto = getCarmenDTOSondeo(cod1, avance).getBody();
        dto.getCpDTO().forEach(cp -> {
            cp.setEscanos_desde(cp.getEscanos_desde_sondeo());
            cp.setEscanos_hasta(cp.getEscanos_hasta_sondeo());
            cp.setPorcentajeVoto(cp.getPorcentajeVotoSondeo());
        });
        csvExportService.writeCarmenDTOToCsv(dto, servletResponse.getWriter());
    }

    @RequestMapping(path = "/oficial/{codigo}/{avance}/excel")
    public void getCarmenDTOInExcel(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=CarmenDTO_" + cod1 + ".xlsx");
        CarmenDTO dto = getCarmenDTOOficial(cod1, avance).getBody();
        List<CarmenDTO> listado = new ArrayList<>();
        listado.add(dto);
        AExcelExportService excelExportService = new AExcelExportService();
        excelExportService.writeToExcel((RandomAccess) listado, 4, servletResponse);
    }

    @RequestMapping(path = "/sondeo/{codigo}/{avance}/excel")
    public void getCarmenDTOInExcelSondeo(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance, HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("application/octet-stream");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=CarmenDTO_" + cod1 + ".xlsx");
        CarmenDTO dto = getCarmenDTOSondeo(cod1, avance).getBody();
        List<CarmenDTO> listado = new ArrayList<>();
        listado.add(dto);
        AExcelExportService excelExportService = new AExcelExportService();
        excelExportService.writeToExcel((RandomAccess) listado, 4, servletResponse);
    }

    @RequestMapping(path = "/{avance}/{codigo}")
    public ResponseEntity<CarmenDTO> getCarmenDto(@PathVariable("codigo") String cod1, @PathVariable("avance") String avance) {
        CarmenDTO res = getCarmenDTOOficial(cod1, avance).getBody();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
