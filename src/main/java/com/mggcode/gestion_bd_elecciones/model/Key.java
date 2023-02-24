package com.mggcode.gestion_bd_elecciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Key implements Serializable {

    @Column(name = "COD_CIRCUNSCRIPCION", nullable = false)
    private String circunscripcion;

    @Column(name = "COD_PARTIDO", nullable = false)
    private String partido;

    public Key() {
    }

    public Key(String codCircunscripcion, String codPartido) {
        this.circunscripcion = codCircunscripcion;
        this.partido = codPartido;
    }
}
