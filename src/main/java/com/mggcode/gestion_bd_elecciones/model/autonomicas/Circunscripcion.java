package com.mggcode.gestion_bd_elecciones.model.autonomicas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "circunscripciones")
public class Circunscripcion {

    @Id
    @Column(name = "CIRCUNSCRIPCION")
    private String codigo;

    @Column(name = "COMUNIDAD")
    private String codigoComunidad;

    @Column(name = "PROVINCIA")
    private String codigoProvincia;

    @Column(name = "MUNICIPIO")
    private String codigoMunicipio;

    @Column(name = "descripcion")
    private String nombreCircunscripcion;

    @Column
    private double escrutado;

    @Column(name = "escanos")
    private int escanios;

    @Column
    private double avance1;

    @Column
    private double avance2;

    @Column
    private double avance3;

    @Column
    private double participacion;

    @Column
    private int votantes;

    @Column(name = "escanos_hist")
    private int escaniosHistoricos;

    @Column(name = "avance1_hist")
    private double avance1Hist;

    @Column(name = "avance2_hist")
    private double avance2Hist;

    @Column(name = "avance3_hist")
    private double avance3Hist;

    @Column(name = "participacion_hist")
    private double participacionHist;

}
