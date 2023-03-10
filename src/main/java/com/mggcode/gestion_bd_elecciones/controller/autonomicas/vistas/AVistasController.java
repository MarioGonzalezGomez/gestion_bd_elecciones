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
        return "circunscripciones";
    }

    @GetMapping("/partidos/vista")
    public String verPartidos(Model model) {
        List<Partido> partidos = aPartidoController.findAll().getBody();
        model.addAttribute("partidos", partidos);
        return "partidos";
    }

    @GetMapping("/cp/vista")
    public String verCPS(Model model) {
        List<CircunscripcionPartido> cps = aCPController.findAll().getBody();
        model.addAttribute("cps", cps);
        return "cps";
    }

    @GetMapping("/cp/mayorias/autonomias/vista")
    public String verCPSporMA(Model model) {
        List<CircunscripcionPartido> cps = aCPController.masVotadosPorAutonomia().getBody();
        model.addAttribute("cps", cps);
        return "cps";
    }

    @GetMapping("/cp/mayorias/provincias/vista")
    public String verCPSporMP(Model model) {
        List<CircunscripcionPartido> cps = aCPController.masVotadosPorProvincia().getBody();
        model.addAttribute("cps", cps);
        return "cps";
    }
}
