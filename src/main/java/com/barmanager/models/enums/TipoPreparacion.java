package com.barmanager.models.enums;

public enum TipoPreparacion {
    BATIDO("Batido", "Agitar todos los ingredientes con hielo  en coctelera"),
    DIRECTO("Directo", "Servir directamente en el vaso"),
    MEZCLADO("Mezclado", "Mezclar con cuchara de bar"),
    LICUADO("Licuado", "Procesar todos los ingredientes en licuadora"),
    MACERADO("Macerado", "macerar ingredientes antes de agregar liquidos"),
    CAPAS("En capas", "Construir coctel en capas"),
    CONSTRUIDO("Construido", "Construir directamente en el vaso");
    
    private final String nombre;
    private final String descripcionBasica;
    
    TipoPreparacion(String nombre, String descripcionBasica) {
        this.nombre = nombre;
        this.descripcionBasica = descripcionBasica;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcionBasica() {
        return descripcionBasica;
    }
}