package com.mggcode.gestion_bd_elecciones.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mggcode.gestion_bd_elecciones.model.Configuracion;
import com.mggcode.gestion_bd_elecciones.service.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

    @Autowired
    private ConfiguracionService configuracionService;

    @GetMapping()
    public String mostrarFormulario(Model model) {
        Configuracion configuracion = configuracionService.cargarConfiguracion();
        model.addAttribute("configuracion", configuracion);
        return "configuraciones";
    }

    @PostMapping()
    public String guardarConfiguracion(@RequestBody String jsonConfiguracion) {
        Configuracion configuracion = convertirJsonAConfiguracion(jsonConfiguracion);
        System.out.println(jsonConfiguracion);
        configuracionService.guardarConfiguracion(configuracion);
        return "redirect:/configuracion";
    }

    private Configuracion convertirJsonAConfiguracion(String jsonConfiguracion) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonConfiguracion, Configuracion.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // Manejar la excepción
        }
        return null;
    }
}
