package com.mggcode.gestion_bd_elecciones.logic.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;

import java.util.Comparator;

public class Comparador implements Comparator<CircunscripcionPartido> {
    @Override
    public int compare(CircunscripcionPartido o1, CircunscripcionPartido o2) {
        return o1.getKey().getCircunscripcion().compareTo(o2.getKey().getCircunscripcion());
    }



}
