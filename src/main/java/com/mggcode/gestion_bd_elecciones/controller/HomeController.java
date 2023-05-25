package com.mggcode.gestion_bd_elecciones.controller;

import com.mggcode.gestion_bd_elecciones.config.AutonomicasDB;
import com.mggcode.gestion_bd_elecciones.config.MunicipalesDB;
import com.mggcode.gestion_bd_elecciones.model.DbActualResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.SQLException;

@Controller
public class HomeController {

    @Autowired
    private AutonomicasDB autonomicasDbConfig;

    @Autowired
    private MunicipalesDB municipalesDbConfig;

    @Autowired
    @Qualifier("autonomicasDatasource")
    private DataSource dataSource;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        return new ResponseEntity<>("Conexión establecida", HttpStatus.OK);
    }

    @GetMapping("/dbactual")
    public ResponseEntity<DbActualResponse> getDbActual() throws SQLException {
        String connectionString = dataSource.getConnection().getMetaData().getURL();
        String resultado;
        if (connectionString.length() > 0) {
            String urlPart = connectionString.substring("jdbc:mysql://".length());
            String currentIp;
            if (urlPart.contains(",")) {
                currentIp = urlPart.substring(0, urlPart.indexOf(','));
            } else {
                currentIp = urlPart.substring(0, urlPart.indexOf(':'));
            }
            resultado = switch (currentIp) {
                case "172.28.51.21" -> "BD PRINCIPAL";
                case "172.28.51.22" -> "BD RESERVA";
                case "localhost" -> "LOCAL";
                default -> "BD NO ESPECIFICADA";
            };
        } else {
            resultado = "BD NO ESPECIFICADA";
        }
        DbActualResponse db = new DbActualResponse(resultado);
        return new ResponseEntity<>(db, HttpStatus.OK);
    }

    @GetMapping("/principal")
    public String cambiarDbPrincipal() {
        autonomicasDbConfig.changeDataSourceAuto("172.28.51.21");
        municipalesDbConfig.changeDataSourceMuni("172.28.51.21");
        return "redirect:";
    }

    @GetMapping("/reserva")
    public String cambiarDbReseva() {
        autonomicasDbConfig.changeDataSourceAuto("172.28.51.22");
        municipalesDbConfig.changeDataSourceMuni("172.28.51.22");
        return "redirect:";
    }

    @GetMapping("/local")
    public String cambiarDbLocal() {
        autonomicasDbConfig.changeDataSourceAuto("127.0.0.1");
        municipalesDbConfig.changeDataSourceMuni("127.0.0.1");
        return "redirect:";
    }

}
