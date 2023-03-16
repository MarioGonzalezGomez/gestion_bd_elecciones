package com.mggcode.gestion_bd_elecciones.controller.autonomicas.ipf;

import com.mggcode.gestion_bd_elecciones.IPF.ConexionIPF;
import com.mggcode.gestion_bd_elecciones.config.ConfigIPF;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/autonomicas")
public class AIPFController {

//    private final ConexionIPF c = ConexionIPF.getConexion();
//    private final ConfigIPF conf = ConfigIPF.getConfiguracion();
//
//
//    @GetMapping("/ejemploIPF/entra")
//    public String entra(Model model) {
//        String bd = ConfigIPF.config.getProperty("BDAutonomicas");
//        c.enviarMensaje("itemset('" + bd + "MAPA/ENTRA','EVENT_RUN');");
//        return "index";
//    }
//
//    @GetMapping("/ejemploIPF/sale")
//    public String sale(Model model) {
//        String bd = ConfigIPF.config.getProperty("BDAutonomicas");
//        c.enviarMensaje("itemset('" + bd + "MAPA/SALE','EVENT_RUN');");
//        return "index";
//    }
}
