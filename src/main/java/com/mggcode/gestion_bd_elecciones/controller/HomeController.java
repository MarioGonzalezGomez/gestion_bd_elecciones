package com.mggcode.gestion_bd_elecciones.controller;

import com.mggcode.gestion_bd_elecciones.config.AutonomicasDB;
import com.mggcode.gestion_bd_elecciones.config.MunicipalesDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private AutonomicasDB autonomicasDbConfig;

    @Autowired
    private MunicipalesDB municipalesDbConfig;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        return new ResponseEntity<>("Conexión establecida", HttpStatus.OK);
    }

    @GetMapping("/principal")
    public String cambiarDbPrincipal() {
        autonomicasDbConfig.changeDataSourceAuto("172.28.51.21");
        municipalesDbConfig.changeDataSourceMuni("172.28.51.21");
        return "redirect:";
    }

    @GetMapping("/reserva")
    public String cambiarDbReseva() {
        autonomicasDbConfig.changeDataSourceAuto("172.28.51.21");
        municipalesDbConfig.changeDataSourceMuni("172.28.51.21");
        return "redirect:";
    }

    @GetMapping("/local")
    public String cambiarDbLocal() {
        autonomicasDbConfig.changeDataSourceAuto("127.0.0.1");
        municipalesDbConfig.changeDataSourceMuni("127.0.0.1");
        return "redirect:";
    }
}
