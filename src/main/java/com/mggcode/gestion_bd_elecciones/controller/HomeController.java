package com.mggcode.gestion_bd_elecciones.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/test")
    public ResponseEntity<String> testConnection(){
        return new ResponseEntity<>("Conexión establecida",HttpStatus.OK);
    }
}
