package com.mggcode.gestion_bd_elecciones.service;

import com.mggcode.gestion_bd_elecciones.model.Configuracion;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class ConfiguracionService {
    private final String rutaConfig = "src/main/resources/config.properties";


    public Configuracion cargarConfiguracion() {
        Properties propiedades = new Properties();
        try {
            FileInputStream archivoEntrada = new FileInputStream(rutaConfig);
            propiedades.load(archivoEntrada);
            archivoEntrada.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Configuracion configuracion = new Configuracion();

        //TODO:Manejar esto como una lista si vemos que hay casos de más conexiones
        configuracion.setIpPrincipal(propiedades.getProperty("ipPrincipal"));
        configuracion.setIpReserva(propiedades.getProperty("ipReserva"));
        configuracion.setRutaFichero(propiedades.getProperty("rutaFichero"));
        return configuracion;
    }

    public void guardarConfiguracion(Configuracion configuracion) {
        try {
            Properties propiedades = new Properties();
            propiedades.setProperty("ipPrincipal", String.valueOf(configuracion.getIpPrincipal()));
            propiedades.setProperty("ipReserva", configuracion.getIpReserva());
            propiedades.setProperty("rutaFichero", configuracion.getRutaFichero());
            File archivoPropiedades = new File(rutaConfig);
            FileOutputStream archivoSalida = new FileOutputStream(archivoPropiedades);
            propiedades.store(archivoSalida, "Archivo de configuración");
            archivoSalida.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
