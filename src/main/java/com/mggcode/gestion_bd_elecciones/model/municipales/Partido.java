package com.mggcode.gestion_bd_elecciones.model.municipales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @Column(name = "PARTIDO")
    private String codigo;

    @Column(name = "sigla")
    private String siglas;

    @Column(name = "padre")
    private String codigoPadre;

    @Column(name = "descripcion")
    private String nombreCompleto;
}
