package com.barmanager.dto;

import com.barmanager.models.enums.EstadoDisponibilidad;
import java.util.List;

public class CoctelDisponibilidadDTO {
    private int coctelId;
    private String nombreCoctel;
    private String descripcion;
    private String categoria;
    private EstadoDisponibilidad estado;
    private int ingredientesDisponibles;
    private int ingredientesTotales;
    private double porcentajeDisponibilidad;
    private List<String> insumosFaltantes;
    private boolean tieneSustituciones;
    private String dificultad;
    
    // Getters y Setters
    public int getCoctelId() { return coctelId; }
    public void setCoctelId(int coctelId) { this.coctelId = coctelId; }
    
    public String getNombreCoctel() { return nombreCoctel; }
    public void setNombreCoctel(String nombreCoctel) { this.nombreCoctel = nombreCoctel; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public EstadoDisponibilidad getEstado() { return estado; }
    public void setEstado(EstadoDisponibilidad estado) { this.estado = estado; }
    
    public int getIngredientesDisponibles() { return ingredientesDisponibles; }
    public void setIngredientesDisponibles(int ingredientesDisponibles) { 
        this.ingredientesDisponibles = ingredientesDisponibles; 
    }
    
    public int getIngredientesTotales() { return ingredientesTotales; }
    public void setIngredientesTotales(int ingredientesTotales) { 
        this.ingredientesTotales = ingredientesTotales; 
    }
    
    public double getPorcentajeDisponibilidad() { return porcentajeDisponibilidad; }
    public void setPorcentajeDisponibilidad(double porcentajeDisponibilidad) { 
        this.porcentajeDisponibilidad = porcentajeDisponibilidad; 
    }
    
    public List<String> getInsumosFaltantes() { return insumosFaltantes; }
    public void setInsumosFaltantes(List<String> insumosFaltantes) { 
        this.insumosFaltantes = insumosFaltantes; 
    }
    
    public boolean isTieneSustituciones() { return tieneSustituciones; }
    public void setTieneSustituciones(boolean tieneSustituciones) { 
        this.tieneSustituciones = tieneSustituciones; 
    }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }
}