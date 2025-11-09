package com.barmanager.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 200)
    private String nombre;
    
    @Column(length = 200)
    private String email;
    
    @Column(unique = true, length = 100)
    private String usuario;
    
    @Column(length = 255)
    private String contrasena;
    
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
    
    @Column(nullable = false)
    private boolean activo;
    
    public Persona() {
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }
    
    public Persona(String nombre, String email) {
        this();
        this.nombre = nombre;
        this.email = email;
    }
    
    public Persona(String nombre, String email, String usuario, String contrasena) {
        this(nombre, email);
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public abstract String getRolDescripcion();
    public abstract boolean puedeAccederInventario();
    public abstract String getTipoRol();
    
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
    
    public String getEmail() { 
        return email; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public String getUsuario() { 
        return usuario; 
    }
    
    public void setUsuario(String usuario) { 
        this.usuario = usuario; 
    }
    
    public String getContrasena() { 
        return contrasena; 
    }
    
    public void setContrasena(String contrasena) { 
        this.contrasena = contrasena; 
    }
    
    public LocalDateTime getFechaRegistro() { 
        return fechaRegistro; 
    }
    
    public void setFechaRegistro(LocalDateTime fechaRegistro) { 
        this.fechaRegistro = fechaRegistro; 
    }
    
    public boolean isActivo() { 
        return activo; 
    }
    
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }
}