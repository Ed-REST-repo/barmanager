package com.barmanager.models.enums;

public enum EstadoInsumo {
    DISPONIBLE("Disponible", "success"),
    BAJO("Bajo Stock", "warning"),
    AGOTADO("Agotado", "danger");
    
    private final String descripcion;
    private final String styleClass;
    // private final String color;
    
    EstadoInsumo(String descripcion, String styleClass) {
        this.descripcion = descripcion;
        this.styleClass = styleClass;
        // this.color = color;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public String getBootstrapClass() {
        return styleClass;
    }
    /* 
    public String getColor() {
        return color;
    }
    */
}