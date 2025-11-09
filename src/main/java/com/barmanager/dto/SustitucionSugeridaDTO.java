package com.barmanager.dto;

public class SustitucionSugeridaDTO {
    private String insumoOriginal;
    private String insumoSustituto;
    private double cantidadNecesaria;
    private String unidad;
    private int calidadSustitucion;
    private String notas;
    
    // Getters y Setters
    public String getInsumoOriginal() { return insumoOriginal; }
    public void setInsumoOriginal(String insumoOriginal) { 
        this.insumoOriginal = insumoOriginal; 
    }
    
    public String getInsumoSustituto() { return insumoSustituto; }
    public void setInsumoSustituto(String insumoSustituto) { 
        this.insumoSustituto = insumoSustituto; 
    }
    
    public double getCantidadNecesaria() { return cantidadNecesaria; }
    public void setCantidadNecesaria(double cantidadNecesaria) { 
        this.cantidadNecesaria = cantidadNecesaria; 
    }
    
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    
    public int getCalidadSustitucion() { return calidadSustitucion; }
    public void setCalidadSustitucion(int calidadSustitucion) { 
        this.calidadSustitucion = calidadSustitucion; 
    }
    
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}