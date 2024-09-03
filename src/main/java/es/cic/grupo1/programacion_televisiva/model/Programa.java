package es.cic.grupo1.programacion_televisiva.model;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Programa {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombre;
    private String descripcion;
    private String clasificacion;
    private String canal;

    private Timestamp fechaIni;
    private Timestamp fechaFin;

    private boolean semanal;
    private boolean favorito;
    
    @Column(name = "dias", nullable = true)
    private List<String> dias = new ArrayList<>(); // Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo

    @ManyToOne 
    @JoinColumn(name = "tipo_programa_id",nullable = true)
    private TipoPrograma tipoPrograma;

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Timestamp getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Timestamp fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isSemanal() {
        return semanal;
    }

    public void setSemanal(boolean semanal) {
        this.semanal = semanal;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
    }

    public TipoPrograma getTipoPrograma() {
        return tipoPrograma;
    }

    public void setTipoPrograma(TipoPrograma tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }


    public boolean isFavorito() {
        return favorito;
    }


  
}
