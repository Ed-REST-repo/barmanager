package com.barmanager.models;

import com.barmanager.models.enums.TipoInsumo;
import com.barmanager.models.enums.EstadoInsumo;
import com.barmanager.exceptions.InsumoInsuficienteException;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "insumos")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInsumo tipo;
    
    @Column(name = "cantidad_disponible", nullable = false)
    private double cantidadDisponible;
    
    @ManyToOne
    @JoinColumn(name = "unidad_id")
    private UnidadMedida unidad;
    
    @Column(name = "cantidad_minima")
    private double cantidadMinima;
    
    private double precio;
    
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
    
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;
    
    @Column(name = "codigo_barras", length = 50)
    private String codigoBarras;
    
    public Insumo() {
        this.cantidadMinima = 0;
        this.cantidadDisponible = 0;
    }
    
    public Insumo(String nombre, TipoInsumo tipo, double cantidad, 
                  UnidadMedida unidad, Proveedor proveedor) {
        this();
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidadDisponible = cantidad;
        this.unidad = unidad;
        this.proveedor = proveedor;
    }
    
    public boolean disponible(double cantidadRequerida) {
        return this.cantidadDisponible >= cantidadRequerida;
    }
    
    public boolean requiereReposicion() {
        return this.cantidadDisponible <= this.cantidadMinima;
    }
    
    public void descontar(double cantidad) throws InsumoInsuficienteException {
        if (!disponible(cantidad)) {
            throw new InsumoInsuficienteException(
                this.nombre, 
                this.cantidadDisponible, 
                cantidad
            );
        }
        this.cantidadDisponible -= cantidad;
    }
    
    public void reponer(double cantidad) {
        this.cantidadDisponible += cantidad;
    }
    
    public EstadoInsumo getEstado() {
        if (cantidadDisponible <= 0) return EstadoInsumo.AGOTADO;
        if (requiereReposicion()) return EstadoInsumo.BAJO;
        return EstadoInsumo.DISPONIBLE;
    }
    
    public boolean estaVencido() {
        if (fechaVencimiento == null) return false;
        return LocalDate.now().isAfter(fechaVencimiento);
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public TipoInsumo getTipo() { return tipo; }
    public void setTipo(TipoInsumo tipo) { this.tipo = tipo; }
    
    public double getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(double cantidadDisponible) { 
        this.cantidadDisponible = cantidadDisponible; 
    }
    
    public UnidadMedida getUnidad() { return unidad; }
    public void setUnidad(UnidadMedida unidad) { this.unidad = unidad; }
    
    public double getCantidadMinima() { return cantidadMinima; }
    public void setCantidadMinima(double cantidadMinima) { 
        this.cantidadMinima = cantidadMinima; 
    }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
    
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { 
        this.fechaVencimiento = fechaVencimiento; 
    }
    
    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
}