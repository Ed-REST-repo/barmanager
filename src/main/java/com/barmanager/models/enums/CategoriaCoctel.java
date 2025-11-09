package com.barmanager.models.enums;

public enum CategoriaCoctel {
    CLASICO("Clasico"),
    TROPICAL("Tropical"),
    CONTEMPORANEO("Contemporaneo"),
    DIGESTIVO("Digestivo"),
    APERITIVO("Aperitivo"),
    SHOT("Shot"),
    MOCKTAIL("Mocktail"),
    TIKI("Tiki"),
    SOUR("Sour"),
    HIGHBALL("Highball");
    
    private final String descripcion;
    
    CategoriaCoctel(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}