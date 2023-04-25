package com.mggcode.gestion_bd_elecciones.logic.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.CircunscripcionPartido;

import java.util.Comparator;

public class CircunscripcionPartidoSondeo implements Comparator<CircunscripcionPartido> {
    //TODO(Desde, hasta sondeo)
    //TODO(
    @Override
    public int compare(CircunscripcionPartido o1, CircunscripcionPartido o2) {
        int comp = Integer.compare(o1.getEscanos_hasta_sondeo(), o2.getEscanos_hasta_sondeo());
        if (comp == 0) {
            comp = Integer.compare(o1.getEscanos_desde_sondeo(), o2.getEscanos_desde_sondeo());
            if (comp == 0) {
                comp = Double.compare(o1.getPorcentajeVotoSondeo(), o2.getPorcentajeVotoSondeo());
            }
        }
        return comp;
    }
}
