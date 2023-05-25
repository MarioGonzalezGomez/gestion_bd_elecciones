package com.mggcode.gestion_bd_elecciones.logic.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;

import java.util.Comparator;

public class CircunscripcionPartidoOficial implements Comparator<CircunscripcionPartido> {
    @Override
    public int compare(CircunscripcionPartido o1, CircunscripcionPartido o2) {
        if(o1.getKey().getPartido().equals("99999"))return -1;
        var comp = Integer.compare(o1.getEscanos_hasta(), o2.getEscanos_hasta());
        if (comp == 0) {
            comp = Double.compare(o1.getPorcentajeVoto(), o2.getPorcentajeVoto());
            if (comp == 0) {
                comp = Integer.compare(o1.getNumVotantes(), o2.getNumVotantes());
            }
        }
        return comp;
    }
}
