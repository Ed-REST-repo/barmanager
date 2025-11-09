package com.barmanager.dto;

import com.barmanager.models.Coctel;
// import com.barmanager.models.Ingrediente;
import com.barmanager.models.PasoPreparacion;
import java.util.List;

public class CoctelDetalladoDTO {
    private Coctel coctel;
    private List<IngredienteDetalleDTO> ingredientes;
    private List<PasoPreparacion> pasos;
    private boolean preparable;
    private List<SustitucionSugeridaDTO> sustituciones;
    private String nivelAlcohol;
    private String nivelCalorico;
    private int tiempoPreparacion;
    private double costoEstimado;
    
    // Getters y Setters
    public Coctel getCoctel() { return coctel; }
    public void setCoctel(Coctel coctel) { this.coctel = coctel; }
    
    public List<IngredienteDetalleDTO> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<IngredienteDetalleDTO> ingredientes) { 
        this.ingredientes = ingredientes; 
    }
    
    public List<PasoPreparacion> getPasos() { return pasos; }
    public void setPasos(List<PasoPreparacion> pasos) { this.pasos = pasos; }
    
    public boolean isPreparable() { return preparable; }
    public void setPreparable(boolean preparable) { this.preparable = preparable; }
    
    public List<SustitucionSugeridaDTO> getSustituciones() { return sustituciones; }
    public void setSustituciones(List<SustitucionSugeridaDTO> sustituciones) { 
        this.sustituciones = sustituciones; 
    }
    
    public String getNivelAlcohol() { return nivelAlcohol; }
    public void setNivelAlcohol(String nivelAlcohol) { this.nivelAlcohol = nivelAlcohol; }
    
    public String getNivelCalorico() { return nivelCalorico; }
    public void setNivelCalorico(String nivelCalorico) { this.nivelCalorico = nivelCalorico; }
    
    public int getTiempoPreparacion() { return tiempoPreparacion; }
    public void setTiempoPreparacion(int tiempoPreparacion) { 
        this.tiempoPreparacion = tiempoPreparacion; 
    }
    
    public double getCostoEstimado() { return costoEstimado; }
    public void setCostoEstimado(double costoEstimado) { this.costoEstimado = costoEstimado; }
}