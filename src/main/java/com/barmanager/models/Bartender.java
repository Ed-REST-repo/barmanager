package com.barmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "bartenders")
public class Bartender extends Persona {
    
    @Column(length = 200)
    private String especialidad;
    
    @Column(name = "cocteles_preparados", nullable = false)
    private int coctelesPreparados;
    
    public Bartender() {
        super();
        this.coctelesPreparados = 0;
    }
    
    public Bartender(String nombre, String email, String especialidad) {
        super(nombre, email);
        this.especialidad = especialidad;
        this.coctelesPreparados = 0;
    }
    
    public Bartender(String nombre, String email, String usuario, 
                     String contrasena, String especialidad) {
        super(nombre, email, usuario, contrasena);
        this.especialidad = especialidad;
        this.coctelesPreparados = 0;
    }
    
    @Override
    public String getRolDescripcion() {
        return "Bartender - Especialista en " + 
               (especialidad != null ? especialidad : "General");
    }
    
    @Override
    public boolean puedeAccederInventario() {
        return false;
    }
    
    @Override
    public String getTipoRol() {
        return "BARTENDER";
    }
    
    public void incrementarCoctelesPreparados() {
        this.coctelesPreparados++;
    }
    
    // Getters y Setters
    public String getEspecialidad() { 
        return especialidad; 
    }
    
    public void setEspecialidad(String especialidad) { 
        this.especialidad = especialidad; 
    }
    
    public int getCoctelesPreparados() { 
        return coctelesPreparados; 
    }
    
    public void setCoctelesPreparados(int coctelesPreparados) { 
        this.coctelesPreparados = coctelesPreparados; 
    }
}