package com.barmanager.services;

import com.barmanager.models.Coctel;
import com.barmanager.models.Ingrediente;
import com.barmanager.models.Insumo;
import com.barmanager.repositories.CoctelRepository;
import com.barmanager.repositories.IngredienteRepository;
import com.barmanager.repositories.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EstadisticasService {
    
    @Autowired
    private CoctelRepository coctelRepository;
    
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    // top cocteles preparados
    public List<Map<String, Object>> obtenerCoctelesMasPreparados(int limite) {
        List<Coctel> cocteles = coctelRepository.findMasPreparados();
        
        return cocteles.stream()
            .limit(limite)
            .filter(c -> c.getVecesPreparado() > 0) // solo los que se han preparado
            .map(c -> {
                Map<String, Object> datos = new HashMap<>();
                datos.put("nombre", c.getNombre());
                datos.put("preparaciones", c.getVecesPreparado());
                datos.put("categoria", c.getCategoria().getDescripcion());
                datos.put("dificultad", c.getDificultad() != null ? 
                    c.getDificultad().getDescripcion() : "N/A");
                return datos;
            })
            .collect(Collectors.toList());
    }
    
    //insumos mas utilizados
    public List<Map<String, Object>> obtenerInsumosMasUtilizados(int limite) {
        List<Coctel> cocteles = coctelRepository.findAll();
        Map<Integer, Integer> usoPorInsumo = new HashMap<>();
        Map<Integer, String> nombresPorInsumo = new HashMap<>();
        
        // contar uso de cada insumo
        for (Coctel coctel : cocteles) {
            if (coctel.getVecesPreparado() > 0) {
                List<Ingrediente> ingredientes = ingredienteRepository.findByCoctelId(coctel.getId());
                for (Ingrediente ing : ingredientes) {
                    int insumoId = ing.getInsumoId();
                    int vecesUsado = coctel.getVecesPreparado();
                    
                    usoPorInsumo.merge(insumoId, vecesUsado, Integer::sum);
                    
                    if (!nombresPorInsumo.containsKey(insumoId)) {
                        Insumo insumo = insumoRepository.findById(insumoId).orElse(null);
                        if (insumo != null) {
                            nombresPorInsumo.put(insumoId, insumo.getNombre());
                        }
                    }
                }
            }
        }
        
        // ordena y toma los top 
        return usoPorInsumo.entrySet().stream()
            .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
            .limit(limite)
            .map(entry -> {
                Map<String, Object> datos = new HashMap<>();
                datos.put("nombre", nombresPorInsumo.get(entry.getKey()));
                datos.put("vecesUtilizado", entry.getValue());
                
                //info del insumo
                Insumo insumo = insumoRepository.findById(entry.getKey()).orElse(null);
                if (insumo != null) {
                    datos.put("tipo", insumo.getTipo());
                    datos.put("disponible", insumo.getCantidadDisponible());
                }
                return datos;
            })
            .collect(Collectors.toList());
    }
    
    // Preparaciones por dificultad
    public List<Map<String, Object>> obtenerPreparacionesPorDificultad() {
        List<Coctel> cocteles = coctelRepository.findAll();
        Map<String, Integer> preparacionesPorDificultad = new HashMap<>();
        
        preparacionesPorDificultad.put("Facil", 0);
        preparacionesPorDificultad.put("Media", 0);
        preparacionesPorDificultad.put("Dificil", 0);
        preparacionesPorDificultad.put("Experto", 0);
        
        //preparaciones por dificultad
        for (Coctel coctel : cocteles) {
            if (coctel.getDificultad() != null && coctel.getVecesPreparado() > 0) {
                String dificultad = coctel.getDificultad().getDescripcion();
                preparacionesPorDificultad.merge(dificultad, coctel.getVecesPreparado(), Integer::sum);
            }
        }
        
        return preparacionesPorDificultad.entrySet().stream()
            .map(entry -> {
                Map<String, Object> datos = new HashMap<>();
                datos.put("dificultad", entry.getKey());
                datos.put("preparaciones", entry.getValue());
                return datos;
            })
            .sorted((a, b) -> {
                // Ordenar por nivel de dificultad
                String[] orden = {"Facil", "Media", "Dificil", "Experto"};
                int indexA = Arrays.asList(orden).indexOf(a.get("dificultad"));
                int indexB = Arrays.asList(orden).indexOf(b.get("dificultad"));
                return Integer.compare(indexA, indexB);
            })
            .collect(Collectors.toList());
    }
    
    // preparaciones realizadas
    public int obtenerTotalPreparaciones() {
        List<Coctel> cocteles = coctelRepository.findAll();
        return cocteles.stream()
            .mapToInt(Coctel::getVecesPreparado)
            .sum();
    }
}