package com.mggcode.gestion_bd_elecciones.logic;

import com.mggcode.gestion_bd_elecciones.config.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaFicheroColores {


    public ArrayList<String[]> leerFicheroColores() {
        Config.getConfiguracion();
        String archivoCSV = Config.getRutaFichero();
        BufferedReader br = null;
        String linea = "";
        String separadorCSV = ";";
        ArrayList<String[]> partidos = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(archivoCSV));
            while ((linea = br.readLine()) != null) {
                String[] partido = linea.split(separadorCSV);
                partidos.add(partido);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return partidos;
    }
}
