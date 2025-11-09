package com.barmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "direcciones")
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 300)
    private String calle;
    
    @Column(length = 100)
    private String ciudad;
    
    @Column(length = 100)
    private String pais;
    
    @Column(name = "codigo_postal", length = 20)
    private String codigoPostal;
    
    public Direccion() {}
    
    public Direccion(String calle, String ciudad, String pais) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.pais = pais;
    }
    
    public String getDireccionCompleta() {
        StringBuilder sb = new StringBuilder();
        if (calle != null) sb.append(calle);
        if (ciudad != null) sb.append(", ").append(ciudad);
        if (pais != null) sb.append(", ").append(pais);
        return sb.toString();
    }
    
    // Getters y Setters
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getCalle() { 
        return calle; 
    }
    
    public void setCalle(String calle) { 
        this.calle = calle; 
    }
    
    public String getCiudad() { 
        return ciudad; 
    }
    
    public void setCiudad(String ciudad) { 
        this.ciudad = ciudad; 
    }
    
    public String getPais() { 
        return pais; 
    }
    
    public void setPais(String pais) { 
        this.pais = pais; 
    }
    
    public String getCodigoPostal() { 
        return codigoPostal; 
    }
    
    public void setCodigoPostal(String codigoPostal) { 
        this.codigoPostal = codigoPostal; 
    }
}