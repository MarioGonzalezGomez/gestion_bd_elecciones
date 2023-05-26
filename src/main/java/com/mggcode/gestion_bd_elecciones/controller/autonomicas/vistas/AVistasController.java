package com.mggcode.gestion_bd_elecciones.controller.autonomicas.vistas;

import com.mggcode.gestion_bd_elecciones.controller.autonomicas.ACircunscripcionController;
import com.mggcode.gestion_bd_elecciones.controller.autonomicas.ACircunscripcionPartidoController;
import com.mggcode.gestion_bd_elecciones.controller.autonomicas.APartidoController;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/autonomicas")
public class AVistasController {
    @Autowired
    private ACircunscripcionController aCircunscripcionController;
    @Autowired
    private APartidoController aPartidoController;

    @Autowired
    private ACircunscripcionPartidoController aCPController;

    @GetMapping("/circunscripciones/vista")
    public String verCircunscripciones(Model model) {
        List<Circunscripcion> circunscripciones = aCircunscripcionController.findAll().getBody();
        model.addAttribute("circunscripciones", circunscripciones);
        model.addAttribute("rutaCSV", "/autonomicas/circunscripciones/csv");
        model.addAttribute("rutaExcel", "/autonomicas/circunscripciones/excel");
        return "circunscripciones";
    }

    @GetMapping("/partidos/vista")
    public String verPartidos(Model model) {
        List<Partido> partidos = aPartidoController.findAll().getBody();
        model.addAttribute("partidos", partidos);
        model.addAttribute("rutaCSV", "/autonomicas/partidos/csv");
        model.addAttribute("rutaExcel", "/autonomicas/partidos/excel");
        return "partidos";
    }

    @GetMapping("/cp/vista")
    public String verCPS(Model model) {
        List<CircunscripcionPartido> cps = aCPController.findAll().getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/excel");
        return "cps";
    }

    @GetMapping("/cp/mayorias/autonomias/vista")
    public String verCPSporMA(Model model) {
        List<CircunscripcionPartido> cps = aCPController.masVotadosPorAutonomia().getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/mayorias/autonomias/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/mayorias/autonomias/excel");
        return "cps";
    }

    @GetMapping("/cp/mayorias/provincias/vista")
    public String verCPSporMP(Model model) {
        List<CircunscripcionPartido> cps = aCPController.masVotadosPorProvincia().getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/mayorias/provincias/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/mayorias/provincias/excel");
        return "cps";
    }

    @GetMapping("/cp/mayorias/{codigo}/vista")
    public String verMayoriasAutonomicas(@PathVariable("codigo") String cod, Model model) {
        List<CircunscripcionPartido> cps = aCPController.masVotadosAutonomicoPorProvinciaOficial(cod).getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/mayorias/" + cod + "/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/mayorias/" + cod + "/excel");
        return "cps";
    }

    @GetMapping("/cp/circunscripcion/{codigo}/vista")
    public String verTodosCPDeUnaCircunscripcion(@PathVariable("codigo") String cod, Model model) {
        List<CircunscripcionPartido> cps = aCPController.findByIdCircunscripcion(cod).getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/circunscripcion/" + cod + "/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/circunscripcion/" + cod + "/excel");
        return "cps";
    }

    @GetMapping("/cp/partido/{codigo}/autonomias/orderByCodAuto/vista")
    public String verTodoDePartidoOrderAuto(@PathVariable("codigo") String cod, Model model) {
        List<CircunscripcionPartido> cps = aCPController.findByIdPartidoAutonomiasCod(cod).getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/partido/" + cod + "/autonomias/orderByCodAuto/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/partido/" + cod + "/autonomias/orderByCodAuto/excel");
        return "cps";
    }

    @GetMapping("/cp/partido/{codigo}/autonomias/orderByEscanios/vista")
    public String verTodoDePartidoOrderEscanios(@PathVariable("codigo") String cod, Model model) {
        List<CircunscripcionPartido> cps = aCPController.findByIdPartidoAutonomiasEscanios(cod).getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/partido/" + cod + "/autonomias/orderByEscanios/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/partido/" + cod + "/autonomias/orderByEscanios/excel");
        return "cps";
    }

    @GetMapping("/cp/partido/{codigo}/provincias/vista")
    public String verTodoDePartidoPorProvincias(@PathVariable("codigo") String cod, Model model) {
        List<CircunscripcionPartido> cps = aCPController.findByIdPartidoProvincias(cod).getBody();
        model.addAttribute("cps", cps);
        model.addAttribute("rutaCSV", "/autonomicas/cp/partido/" + cod + "/provincias/csv");
        model.addAttribute("rutaExcel", "/autonomicas/cp/partido/" + cod + "/provincias/excel");
        return "cps";
    }
}
