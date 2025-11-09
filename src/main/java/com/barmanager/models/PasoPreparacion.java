package com.barmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "pasos_preparacion")
public class PasoPreparacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private int orden;
    
    @Column(nullable = false, length = 1000)
    private String descripcion;
    
    @Column(name = "duracion_segundos")
    private int duracionSegundos;
    
    // @Column(name = "imagen_url", length = 500)
    // private String imagenUrl;
    
    public PasoPreparacion() {}
    
    public PasoPreparacion(int orden, String descripcion) {
        this.orden = orden;
        this.descripcion = descripcion;
    }
    
    public PasoPreparacion(int orden, String descripcion, int duracionSegundos) {
        this(orden, descripcion);
        this.duracionSegundos = duracionSegundos;
    }
    
    // Getters y Setters
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }
    
    public int getOrden() { 
        return orden; 
    }
    
    public void setOrden(int orden) { 
        this.orden = orden; 
    }
    
    public String getDescripcion() { 
        return descripcion; 
    }
    
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }
    
    public int getDuracionSegundos() { 
        return duracionSegundos; 
    }
    
    public void setDuracionSegundos(int duracionSegundos) { 
        this.duracionSegundos = duracionSegundos; 
    }
    
    // public String getImagenUrl() { 
    //     return imagenUrl; 
    // }
    
    // public void setImagenUrl(String imagenUrl) { 
    //     this.imagenUrl = imagenUrl; 
    // }
}