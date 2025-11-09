package com.barmanager.models.enums;

public enum DificultadPreparacion {
    FACIL("Facil", 1, "*"),
    MEDIA("Media", 2, "**"),
    DIFICIL("Dificil", 3, "***"),
    EXPERTO("Experto", 4, "****");
    
    private final String descripcion;
    private final int nivel;
    private final String estrellas;
    
    DificultadPreparacion(String descripcion, int nivel, String estrellas) {
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.estrellas = estrellas;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public String getEstrellas() {
        return estrellas;
    }
}