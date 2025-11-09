package com.barmanager.exceptions;

public class InsumoInsuficienteException extends Exception {
    private String nombreInsumo;
    private double cantidadDisponible;
    private double cantidadRequerida;
    
    public InsumoInsuficienteException(String mensaje) {
        super(mensaje);
    }
    
    public InsumoInsuficienteException(String nombreInsumo, 
                                       double disponible, 
                                       double requerida) {
        super(String.format("Insumo '%s' insuficiente. Disponible: %.2f, Requerido: %.2f",
            nombreInsumo, disponible, requerida));
        this.nombreInsumo = nombreInsumo;
        this.cantidadDisponible = disponible;
        this.cantidadRequerida = requerida;
    }
    
    public InsumoInsuficienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
    // Getters
    public String getNombreInsumo() {
        return nombreInsumo;
    }
    
    public double getCantidadDisponible() {
        return cantidadDisponible;
    }
    
    public double getCantidadRequerida() {
        return cantidadRequerida;
    }
    
    public double getFaltante() {
        return cantidadRequerida - cantidadDisponible;
    }
}