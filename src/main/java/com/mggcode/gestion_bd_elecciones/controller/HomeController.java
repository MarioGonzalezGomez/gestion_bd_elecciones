package com.mggcode.gestion_bd_elecciones.controller;


import com.mggcode.gestion_bd_elecciones.controller.municipales.PartidoController;
import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private PartidoController partidoController;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("nombre", "<strong>Mundo<strong>");
        return "index";
    }

    @GetMapping("vista/partidos")
    public String verPartido(Model model) {
        List<Partido> partidos = partidoController.findAll().getBody();
        model.addAttribute("partidos", partidos);
        return "partido";
    }
}
