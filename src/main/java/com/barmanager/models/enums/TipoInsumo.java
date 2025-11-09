package com.barmanager.models.enums;

public enum TipoInsumo {
    LICOR("Licor", "ml"),
    MIXER("Mixer", "ml"),
    GARNISH("Garnish", "unidades"),
    JARABE("Jarabe", "ml"),
    FRUTA("Fruta", "unidades"),
    HIERBAS("Hierbas", "gramos"),
    ESPECIAS("Especias", "gramos"),
    BITTER("Bitter", "ml"),
    CREMA("Crema", "ml");
    
    private final String descripcion;
    private final String unidadPorDefecto;
    
    TipoInsumo(String descripcion, String unidadPorDefecto) {
        this.descripcion = descripcion;
        this.unidadPorDefecto = unidadPorDefecto;
    }
    
    public String getDescripcion() { 
        return descripcion; 
    }
    
    public String getUnidadPorDefecto() { 
        return unidadPorDefecto; 
    }
}