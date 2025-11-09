package com.barmanager.models;

import com.barmanager.models.enums.TipoUnidad;
import javax.persistence.*;

@Entity
@Table(name = "unidades_medida")
public class UnidadMedida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 20)
    private String abreviatura;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUnidad tipo;
    
    public UnidadMedida() {}
    
    public UnidadMedida(String nombre) {
        this.nombre = nombre;
        this.abreviatura = deducirAbreviatura(nombre);
        this.tipo = deducirTipo(nombre);
    }
    
    public UnidadMedida(String nombre, String abreviatura, TipoUnidad tipo) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.tipo = tipo;
    }
    
    private String deducirAbreviatura(String nombre) {
        if (nombre == null) return "";
        return nombre.length() > 2 ? nombre.substring(0, 2) : nombre;
    }
    
    private TipoUnidad deducirTipo(String nombre) {
        if (nombre == null) return TipoUnidad.CANTIDAD;
        
        String n = nombre.toLowerCase();
        if (n.contains("ml") || n.contains("litro") || n.contains("oz")) {
            return TipoUnidad.VOLUMEN;
        }
        if (n.contains("gr") || n.contains("kg") || n.contains("gramo")) {
            return TipoUnidad.PESO;
        }
        return TipoUnidad.CANTIDAD;
    }
    
    // Getters y Setters
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    
    public String getAbreviatura() { 
        return abreviatura; 
    }
    
    public void setAbreviatura(String abreviatura) { 
        this.abreviatura = abreviatura; 
    }
    
    public TipoUnidad getTipo() { 
        return tipo; 
    }
    
    public void setTipo(TipoUnidad tipo) { 
        this.tipo = tipo; 
    }
}