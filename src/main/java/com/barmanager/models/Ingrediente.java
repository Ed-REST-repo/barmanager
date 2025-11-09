package com.barmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "coctel_id", nullable = false)
    private int coctelId;
    
    @Column(name = "insumo_id", nullable = false)
    private int insumoId;
    
    @Column(nullable = false)
    private double cantidad;
    
    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private UnidadMedida unidad;
    
    @Column(name = "es_opcional")
    private boolean esOpcional;
    
    @Column(length = 200)
    private String notas;
    
    private int orden;
    
    @Column(name = "costo_unitario")
    private double costoUnitario;
    
    @Transient
    private String nombreInsumo;
    
    @Transient
    private String nombreCoctel;
    
    public Ingrediente() {
        this.esOpcional = false;
        this.orden = 0;
    }
    
    public Ingrediente(int coctelId, int insumoId, double cantidad, UnidadMedida unidad) {
        this();
        this.coctelId = coctelId;
        this.insumoId = insumoId;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }
    
    public double getCostoIngrediente() {
        return costoUnitario * cantidad;
    }
    
    public String getCantidadFormateada() {
        return String.format("%.2f %s", cantidad, 
            unidad != null ? unidad.getAbreviatura() : "");
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getCoctelId() { return coctelId; }
    public void setCoctelId(int coctelId) { this.coctelId = coctelId; }
    
    public int getInsumoId() { return insumoId; }
    public void setInsumoId(int insumoId) { this.insumoId = insumoId; }
    
    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
    
    public UnidadMedida getUnidad() { return unidad; }
    public void setUnidad(UnidadMedida unidad) { this.unidad = unidad; }
    
    public boolean esOpcional() { return esOpcional; }
    public void setEsOpcional(boolean esOpcional) { this.esOpcional = esOpcional; }
    
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
    
    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }
    
    public double getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(double costoUnitario) { this.costoUnitario = costoUnitario; }
    
    public String getNombreInsumo() { return nombreInsumo; }
    public void setNombreInsumo(String nombreInsumo) { this.nombreInsumo = nombreInsumo; }
    
    public String getNombreCoctel() { return nombreCoctel; }
    public void setNombreCoctel(String nombreCoctel) { this.nombreCoctel = nombreCoctel; }
}