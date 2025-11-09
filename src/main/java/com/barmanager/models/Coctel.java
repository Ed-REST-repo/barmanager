package com.barmanager.models;

import com.barmanager.models.enums.CategoriaCoctel;
import com.barmanager.models.enums.DificultadPreparacion;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cocteles")
public class Coctel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(length = 500)
    private String descripcion;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaCoctel categoria;
    
    @Enumerated(EnumType.STRING)
    private DificultadPreparacion dificultad;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preparacion_id")
    private Preparacion metodoPreparacion;
    
    @OneToMany(mappedBy = "coctelId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingrediente> ingredientes;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_coctel_id")
    private InfoCoctel infoCoctel;
    
    @Column(name = "veces_preparado")
    private int vecesPreparado;
    
    @Column(name = "calificacion_promedio")
    private double calificacionPromedio;
    
    @Column(length = 50)
    private String vaso;
    
    @Column(length = 50)
    private String hielo;
    
    public Coctel() {
        this.ingredientes = new ArrayList<>();
        this.vecesPreparado = 0;
        this.calificacionPromedio = 0.0;
    }
    
    // Constructores y métodos (mantener los existentes)
    public Coctel(String nombre, CategoriaCoctel categoria) {
        this();
        this.nombre = nombre;
        this.categoria = categoria;
    }
    
    public void agregarIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }
    
    public boolean sePuedePreparar(Map<Integer, Insumo> inventario) {
        for (Ingrediente ingrediente : ingredientes) {
            if (ingrediente.esOpcional()) continue;
            
            Insumo insumo = inventario.get(ingrediente.getInsumoId());
            if (insumo == null || !insumo.disponible(ingrediente.getCantidad())) {
                return false;
            }
        }
        return true;
    }
    
    public List<Insumo> obtenerInsumosFaltantes(Map<Integer, Insumo> inventario) {
        List<Insumo> faltantes = new ArrayList<>();
        for (Ingrediente ingrediente : ingredientes) {
            if (ingrediente.esOpcional()) continue;
            
            Insumo insumo = inventario.get(ingrediente.getInsumoId());
            if (insumo == null || !insumo.disponible(ingrediente.getCantidad())) {
                if (insumo != null) {
                    faltantes.add(insumo);
                }
            }
        }
        return faltantes;
    }
    
    public void registrarPreparacion() {
        this.vecesPreparado++;
    }
    
    public double getCostoTotal() {
        double costo = 0.0;
        for (Ingrediente detalle : ingredientes) {
            costo += detalle.getCostoIngrediente();
        }
        return costo;
    }
    
    // Getters y Setters (mantener todos)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public CategoriaCoctel getCategoria() { return categoria; }
    public void setCategoria(CategoriaCoctel categoria) { this.categoria = categoria; }
    
    public DificultadPreparacion getDificultad() { return dificultad; }
    public void setDificultad(DificultadPreparacion dificultad) { this.dificultad = dificultad; }
    
    public Preparacion getMetodoPreparacion() { return metodoPreparacion; }
    public void setMetodoPreparacion(Preparacion metodoPreparacion) { 
        this.metodoPreparacion = metodoPreparacion; 
    }
    
    public List<Ingrediente> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<Ingrediente> ingredientes) { 
        this.ingredientes = ingredientes; 
    }
    
    public InfoCoctel getInfoCoctel() { return infoCoctel; }
    public void setInfoCoctel(InfoCoctel infoCoctel) { 
        this.infoCoctel = infoCoctel; 
    }
    
    public int getVecesPreparado() { return vecesPreparado; }
    public void setVecesPreparado(int vecesPreparado) { 
        this.vecesPreparado = vecesPreparado; 
    }
    
    public double getCalificacionPromedio() { return calificacionPromedio; }
    public void setCalificacionPromedio(double calificacionPromedio) { 
        this.calificacionPromedio = calificacionPromedio; 
    }
    
    public String getVaso() { return vaso; }
    public void setVaso(String vaso) { this.vaso = vaso; }
    
    public String getHielo() { return hielo; }
    public void setHielo(String hielo) { this.hielo = hielo; }
}