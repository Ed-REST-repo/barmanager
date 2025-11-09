package com.barmanager.dto;

public class IngredienteDetalleDTO {
    private String nombreInsumo;
    private double cantidad;
    private String unidad;
    private boolean disponible;
    private double cantidadDisponible;
    private boolean esOpcional;
    private String notas;
    
    // Getters y Setters
    public String getNombreInsumo() { return nombreInsumo; }
    public void setNombreInsumo(String nombreInsumo) { this.nombreInsumo = nombreInsumo; }
    
    public double getCantidad() { return cantidad; }
    public void setCantidad(double cantidad) { this.cantidad = cantidad; }
    
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    public double getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(double cantidadDisponible) { 
        this.cantidadDisponible = cantidadDisponible; 
    }
    
    public boolean isEsOpcional() { return esOpcional; }
    public void setEsOpcional(boolean esOpcional) { this.esOpcional = esOpcional; }
    
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
}