package com.barmanager.models;

import javax.persistence.*;

@Entity
@Table(name = "info_cocteles")
public class InfoCoctel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private double calorias;
    
    @Column(nullable = false)
    private double azucares;
    
    @Column(name = "alcohol_porcentaje", nullable = false)
    private double alcoholPorcentaje;
    
    @Column(nullable = false)
    private double carbohidratos;
    
    @Column(nullable = false)
    private double proteinas;
    
    @Column(nullable = false)
    private double grasas;
    
    // @Column(name = "apto_para_veganos")
    // private boolean aptoParaVeganos;
    
    // @Column(name = "apto_para_celiacos")
    // private boolean aptoParaCeliacos;
    
    public InfoCoctel() {
        // this.aptoParaVeganos = true;
        // this.aptoParaCeliacos = true;
    }
    
    public InfoCoctel(double calorias, double alcoholPorcentaje) {
        this();
        this.calorias = calorias;
        this.alcoholPorcentaje = alcoholPorcentaje;
    }
    
    public InfoCoctel(double calorias, double alcoholPorcentaje, 
                      double azucares, boolean vegano, boolean celiaco) {
        this(calorias, alcoholPorcentaje);
        this.azucares = azucares;
        // this.aptoParaVeganos = vegano;
        // this.aptoParaCeliacos = celiaco;
    }
    
    // negocio
    public String getNivelAlcohol() {
        if (alcoholPorcentaje == 0) return "Sin alcohol";
        if (alcoholPorcentaje < 10) return "Bajo";
        if (alcoholPorcentaje < 20) return "Medio";
        return "Alto";
    }
    
    public String getNivelCalorico() {
        if (calorias < 100) return "Bajo";
        if (calorias < 200) return "Moderado";
        return "Alto";
    }
    
    public boolean esAptoDiabeticos() {
        return azucares < 10;
    }
    
    // Getters y Setters
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }
    
    public double getCalorias() { 
        return calorias; 
    }
    
    public void setCalorias(double calorias) { 
        this.calorias = calorias; 
    }
    
    public double getAzucares() { 
        return azucares; 
    }
    
    public void setAzucares(double azucares) { 
        this.azucares = azucares; 
    }
    
    public double getAlcoholPorcentaje() { 
        return alcoholPorcentaje; 
    }
    
    public void setAlcoholPorcentaje(double alcoholPorcentaje) { 
        this.alcoholPorcentaje = alcoholPorcentaje; 
    }
    
    public double getCarbohidratos() { 
        return carbohidratos; 
    }
    
    public void setCarbohidratos(double carbohidratos) { 
        this.carbohidratos = carbohidratos; 
    }
    
    public double getProteinas() { 
        return proteinas; 
    }
    
    public void setProteinas(double proteinas) { 
        this.proteinas = proteinas; 
    }
    
    public double getGrasas() { 
        return grasas; 
    }
    
    public void setGrasas(double grasas) { 
        this.grasas = grasas; 
    }

    /* 
    public boolean isAptoParaVeganos() { 
        return aptoParaVeganos; 
    }
    
    public void setAptoParaVeganos(boolean aptoParaVeganos) { 
        this.aptoParaVeganos = aptoParaVeganos; 
    }
    
    public boolean isAptoParaCeliacos() { 
        return aptoParaCeliacos; 
    }
    
    public void setAptoParaCeliacos(boolean aptoParaCeliacos) { 
        this.aptoParaCeliacos = aptoParaCeliacos; 
    }
    */
}