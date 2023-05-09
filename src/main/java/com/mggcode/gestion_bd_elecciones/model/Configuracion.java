package com.mggcode.gestion_bd_elecciones.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Configuracion {

    private String rutaFichero;
    private String ipPrincipal;
    private String ipReserva;
}
