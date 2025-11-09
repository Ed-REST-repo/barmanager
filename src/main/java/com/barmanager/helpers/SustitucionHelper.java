package com.barmanager.helpers;

import com.barmanager.models.*;
import com.barmanager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class SustitucionHelper {
    
    @Autowired
    private SustitucionRepository sustitucionRepository;
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    /* Busca la mejor sustitucion disponible para un insumo */
    public Optional<Sustitucion> buscarMejorSustitucion(
            int insumoId, 
            double cantidadRequerida) {
        
        if (insumoId <= 0) {
            return Optional.empty();
        }
        
        if (cantidadRequerida <= 0) {
            return Optional.empty();
        }
        
        List<Sustitucion> sustituciones = sustitucionRepository
            .findSustitutosDisponibles(insumoId, cantidadRequerida);
        
        return sustituciones.stream()
            .max(Comparator.comparingInt(Sustitucion::getCalidadSustitucion));
    }
    
    /* Crea sustituciones automaticas basadas en tipo de insumo */
    public List<Sustitucion> sugerirSustitucionesAutomaticas(Insumo insumo) {
        if (insumo == null) {
            return new ArrayList<>();
        }
        
        List<Sustitucion> sugerencias = new ArrayList<>();
        
        // Buscar insumos del mismo tipo que esten disponibles
        List<Insumo> similares = insumoRepository.findByTipo(insumo.getTipo());
        
        for (Insumo similar : similares) {
            if (similar.getId() != insumo.getId() && 
                similar.getCantidadDisponible() > 0) {
                
                Sustitucion sust = new Sustitucion();
                sust.setInsumoOriginal(insumo);
                sust.setInsumoSustituto(similar);
                sust.setFactorConversion(calcularFactorConversion(insumo, similar));
                sust.setCalidadSustitucion(calcularCalidadSustitucion(insumo, similar));
                sust.setNotas("Sustitucion automática sugerida por tipo similar");
                
                sugerencias.add(sust);
            }
        }
        
        return sugerencias;
    }
    
    /* Calcula factor de conversion entre dos insumos */
    private double calcularFactorConversion(Insumo original, Insumo sustituto) {
        if (original == null || sustituto == null) {
            return 1.0;
        }
        // Por defecto 1:1, pero podría basarse en potencia alcoholica 
        return 1.0;
    }
    
    /* Calcula calidad de sustitucion (1-5) */
    private int calcularCalidadSustitucion(Insumo original, Insumo sustituto) {
        if (original == null || sustituto == null) {
            return 2;
        }
        
        // Mismo tipo = mayor calidad
        if (original.getTipo() == sustituto.getTipo()) {
            return 4; // Buena sustitucion
        }
        return 2; // Sustitucion mala
    }
    
    /* Valida que una sustitucion sea viable */
    public boolean validarSustitucion(
            Sustitucion sustitucion, 
            double cantidadRequerida) {
        
        if (sustitucion == null) {
            return false;
        }
        
        if (sustitucion.getInsumoSustituto() == null) {
            return false;
        }
        
        if (cantidadRequerida <= 0) {
            return false;
        }
        
        double cantidadNecesaria = cantidadRequerida * sustitucion.getFactorConversion();
        return sustitucion.getInsumoSustituto().disponible(cantidadNecesaria);
    }
}