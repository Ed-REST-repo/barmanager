package com.barmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(length = 50)
    private String telefono;
    
    @Column(length = 200)
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;
    
    @Column(nullable = false)
    private boolean activo;
    
    @Column(length = 50)
    private String nit;
    
    public Proveedor() {
        this.activo = true;
    }
    
    public Proveedor(String nombre, String telefono) {
        this();
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public Proveedor(String nombre, String telefono, String email) {
        this(nombre, telefono);
        this.email = email;
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
    
    public String getTelefono() { 
        return telefono; 
    }
    
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public Direccion getDireccion() { 
        return direccion; 
    }
    
    public void setDireccion(Direccion direccion) { 
        this.direccion = direccion; 
    }
    
    public boolean isActivo() { 
        return activo; 
    }
    
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }
    
    public String getNit() { 
        return nit; 
    }
    
    public void setNit(String nit) { 
        this.nit = nit; 
    }
}