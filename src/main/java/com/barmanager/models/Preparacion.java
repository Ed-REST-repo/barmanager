package com.barmanager.models;

import com.barmanager.models.enums.TipoPreparacion;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preparaciones")
public class Preparacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPreparacion tipo;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "preparacion_id")
    @OrderBy("orden ASC")
    private List<PasoPreparacion> pasos;
    
    @Column(name = "tiempo_estimado_minutos", nullable = false)
    private int tiempoEstimadoMinutos;
    
    @Column(name = "utensilios_necesarios", length = 500)
    private String utensiliosNecesarios;
    
    @Column(name = "tecnica_especial", length = 500)
    private String tecnicaEspecial;
    
    public Preparacion() {
        this.pasos = new ArrayList<>();
        this.tiempoEstimadoMinutos = 5;
    }
    
    public Preparacion(TipoPreparacion tipo) {
        this();
        this.tipo = tipo;
    }
    
    public Preparacion(TipoPreparacion tipo, int tiempoEstimado) {
        this(tipo);
        this.tiempoEstimadoMinutos = tiempoEstimado;
    }
    
    // negocio
    public void agregarPaso(PasoPreparacion paso) {
        this.pasos.add(paso);
    }
    
    public String getInstruccionesCompletas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo: ").append(tipo).append("\n\n");
        
        if (utensiliosNecesarios != null) {
            sb.append("Utensilios: ").append(utensiliosNecesarios).append("\n\n");
        }
        
        sb.append("Pasos:\n");
        for (int i = 0; i < pasos.size(); i++) {
            sb.append((i + 1)).append(". ")
              .append(pasos.get(i).getDescripcion())
              .append("\n");
        }
        
        sb.append("\nTiempo estimado: ").append(tiempoEstimadoMinutos).append(" minutos");
        
        return sb.toString();
    }
    
    public int getTotalPasos() {
        return pasos.size();
    }
    
    // Getters y Setters
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }
    
    public TipoPreparacion getTipo() { 
        return tipo; 
    }
    
    public void setTipo(TipoPreparacion tipo) { 
        this.tipo = tipo; 
    }
    
    public List<PasoPreparacion> getPasos() { 
        return pasos; 
    }
    
    public void setPasos(List<PasoPreparacion> pasos) { 
        this.pasos = pasos; 
    }
    
    public int getTiempoEstimadoMinutos() { 
        return tiempoEstimadoMinutos; 
    }
    
    public void setTiempoEstimadoMinutos(int tiempoEstimadoMinutos) { 
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos; 
    }
    
    public String getUtensiliosNecesarios() { 
        return utensiliosNecesarios; 
    }
    
    public void setUtensiliosNecesarios(String utensiliosNecesarios) { 
        this.utensiliosNecesarios = utensiliosNecesarios; 
    }
    
    public String getTecnicaEspecial() { 
        return tecnicaEspecial; 
    }
    
    public void setTecnicaEspecial(String tecnicaEspecial) { 
        this.tecnicaEspecial = tecnicaEspecial; 
    }
}