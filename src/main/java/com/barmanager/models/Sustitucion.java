package com.barmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "sustituciones")
public class Sustitucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "insumo_original_id", nullable = false)
    private Insumo insumoOriginal;
    
    @ManyToOne
    @JoinColumn(name = "insumo_sustituto_id", nullable = false)
    private Insumo insumoSustituto;
    
    @Column(name = "factor_conversion")
    private double factorConversion; //si se necesita cantidad veces mas del sustituto
    
    @Column(length = 300)
    private String notas;
    
    @Column(name = "calidad_sustitucion")
    private int calidadSustitucion; // 1-5        5  es sustitucion perfecta
    
    public Sustitucion() {
        this.factorConversion = 1.0;
        this.calidadSustitucion = 3;
    }
    
    public Sustitucion(Insumo original, Insumo sustituto) {
        this();
        this.insumoOriginal = original;
        this.insumoSustituto = sustituto;
    }
    
    public Sustitucion(Insumo original, Insumo sustituto, double factor, int calidad) {
        this(original, sustituto);
        this.factorConversion = factor;
        this.calidadSustitucion = calidad;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Insumo getInsumoOriginal() { return insumoOriginal; }
    public void setInsumoOriginal(Insumo insumoOriginal) { 
        this.insumoOriginal = insumoOriginal; 
    }
    
    public Insumo getInsumoSustituto() { return insumoSustituto; }
    public void setInsumoSustituto(Insumo insumoSustituto) { 
        this.insumoSustituto = insumoSustituto; 
    }
    
    public double getFactorConversion() { return factorConversion; }
    public void setFactorConversion(double factorConversion) { 
        this.factorConversion = factorConversion; 
    }
    
    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }
    
    public int getCalidadSustitucion() { return calidadSustitucion; }
    public void setCalidadSustitucion(int calidadSustitucion) { 
        this.calidadSustitucion = calidadSustitucion; 
    }
}