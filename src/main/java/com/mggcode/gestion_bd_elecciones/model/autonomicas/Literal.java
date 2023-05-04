package com.mggcode.gestion_bd_elecciones.model.autonomicas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "idiomas")
public class Literal {
    @Id
    @Column(name = "ID")
    private int id;
    @Column
    private String castellano;
    @Column
    private String catalan;
    @Column
    private String vasco;
    @Column
    private String gallego;
    @Column
    private String valenciano;
    @Column
    private String mallorquin;

}
