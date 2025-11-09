package com.barmanager.models.enums;

public enum TipoUnidad {
    VOLUMEN("Volumen"),
    PESO("Peso"),
    CANTIDAD("Cantidad");
    
    private final String descripcion;
    
    TipoUnidad(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}