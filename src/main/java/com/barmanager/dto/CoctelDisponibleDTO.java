package com.barmanager.dto;

import com.barmanager.models.Coctel;
import com.barmanager.models.Insumo;
import java.util.List;

public class CoctelDisponibleDTO {
    private Coctel coctel;
    private boolean disponible;
    private List<Insumo> insumosFaltantes;
    
    // Getters y Setters
    public Coctel getCoctel() { return coctel; }
    public void setCoctel(Coctel coctel) { this.coctel = coctel; }
    
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    public List<Insumo> getInsumosFaltantes() { return insumosFaltantes; }
    public void setInsumosFaltantes(List<Insumo> insumosFaltantes) { 
        this.insumosFaltantes = insumosFaltantes; 
    }
}