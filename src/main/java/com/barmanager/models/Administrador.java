package com.barmanager.models;

import com.barmanager.models.enums.NivelAcceso;
import javax.persistence.*;

@Entity
@Table(name = "administradores")
public class Administrador extends Persona {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acceso", nullable = false)
    private NivelAcceso nivelAcceso;
    
    public Administrador() {
        super();
        this.nivelAcceso = NivelAcceso.BASICO;
    }
    
    public Administrador(String nombre, String email, NivelAcceso nivel) {
        super(nombre, email);
        this.nivelAcceso = nivel;
    }
    
    public Administrador(String nombre, String email, String usuario, 
                        String contrasena, NivelAcceso nivel) {
        super(nombre, email, usuario, contrasena);
        this.nivelAcceso = nivel;
    }
    
    @Override
    public String getRolDescripcion() {
        return "Administrador - Nivel " + nivelAcceso;
    }
    
    @Override
    public boolean puedeAccederInventario() {
        return true;
    }
    
    @Override
    public String getTipoRol() {
        return "ADMINISTRADOR";
    }
    
    public boolean puedeEliminar() {
        return nivelAcceso == NivelAcceso.TOTAL;
    }
    
    // Getters y Setters
    public NivelAcceso getNivelAcceso() { 
        return nivelAcceso; 
    }
    
    public void setNivelAcceso(NivelAcceso nivelAcceso) { 
        this.nivelAcceso = nivelAcceso; 
    }
}