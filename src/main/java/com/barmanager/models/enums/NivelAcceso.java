package com.barmanager.models.enums;

public enum NivelAcceso {
    BASICO("Basico", 1),
    INTERMEDIO("Intermedio", 2),
    TOTAL("Total", 3);
    
    private final String descripcion;
    private final int nivel;
    
    NivelAcceso(String descripcion, int nivel) {
        this.descripcion = descripcion;
        this.nivel = nivel;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    public boolean esMayorQue(NivelAcceso otro) {
        return this.nivel > otro.nivel;
    }
}