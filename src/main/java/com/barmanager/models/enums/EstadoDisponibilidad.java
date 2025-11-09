package com.barmanager.models.enums;

public enum EstadoDisponibilidad {
    DISPONIBLE("Disponible", "success"),
    PREPARABLE_CON_SUSTITUCIONES("Preparable con sustituciones", "warning"),
    NO_DISPONIBLE("No disponible", "danger");
    
    private final String descripcion;
    private final String styleClass;
    
    EstadoDisponibilidad(String descripcion, String styleClass) {
        this.descripcion = descripcion;
        this.styleClass = styleClass;
    }
    
    public String getDescripcion() { return descripcion; }
    public String getStyleClass() { return styleClass; }
}