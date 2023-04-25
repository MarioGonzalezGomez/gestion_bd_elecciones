package com.mggcode.gestion_bd_elecciones.logic.autonomicas;

import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;

import java.util.Comparator;

public class CircunscripcionPartidoOficial implements Comparator<CircunscripcionPartido> {
    //TODO(Desde, hasta sondeo)
    //TODO(
    @Override
    public int compare(CircunscripcionPartido o1, CircunscripcionPartido o2) {
        if (o1.getEscanos_hasta() == o2.getEscanos_hasta()) {
            if (o1.getNumVotantes() == o2.getNumVotantes()) {
                return 0;
            }
            return Integer.compare(o1.getNumVotantes(), o2.getNumVotantes());
        }
        return Integer.compare(o1.getEscanos_hasta(), o2.getEscanos_hasta());
    }
}
