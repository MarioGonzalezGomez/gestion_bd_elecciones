package com.mggcode.gestion_bd_elecciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@SpringBootApplication
public class GestionBdEleccionesApplication {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static void main(String[] args) {
        SpringApplication.run(GestionBdEleccionesApplication.class, args);
    }

    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        //System.out.println("Abriendo parte gráfica");
        //browse("http://localhost:8080");
        System.out.println(ANSI_GREEN + "INICIANDO CLIENTE" + ANSI_RESET);
        //runClient();
    }

    public static void runClient() {
        String ruta = Paths.get("").toAbsolutePath().toString() + "\\script.bat";
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", ruta);
            pb.inheritIO();
            Process proceso = pb.start();

            int resultado = proceso.waitFor();

            if (resultado == 0) {
                System.out.println("El archivo .bat se ejecutó correctamente.");
            } else {
                System.out.println("Se produjo un error al ejecutar el archivo .bat.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void browse(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
