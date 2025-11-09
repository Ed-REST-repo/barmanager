package com.barmanager.helpers;

import com.barmanager.models.*;
import com.barmanager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.*;

@Component
public class InventarioHelper {
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    /* Obtiene mapa de inventario para consultas rápidas */
    public Map<Integer, Insumo> obtenerMapaInventario() {
        List<Insumo> insumos = insumoRepository.findAll();
        Map<Integer, Insumo> mapa = new HashMap<>();
        for (Insumo insumo : insumos) {
            mapa.put(insumo.getId(), insumo);
        }
        return mapa;
    }
    
    /* Genera reporte de insumos críticos*/
    public Map<String, List<Insumo>> obtenerInsumosCriticos() {
        Map<String, List<Insumo>> reporte = new HashMap<>();
        
        reporte.put("agotados", insumoRepository.findInsumosAgotados());
        reporte.put("bajoStock", insumoRepository.findInsumosBajoStock());
        reporte.put("porVencer", insumoRepository.findInsumosPorVencer(
            LocalDate.now().plusDays(7)
        ));
        
        return reporte;
    }
    
    /* Calcula valor total del inventario */
    public double calcularValorInventario() {
        return insumoRepository.findAll().stream()
            .mapToDouble(i -> i.getCantidadDisponible() * i.getPrecio())
            .sum();
    }
    
    /** Proyecta consumo estimado para un coctel*/
    public Map<String, Object> proyectarConsumo(
            Coctel coctel, 
            int cantidadPreparaciones) {
        
        Map<String, Object> proyeccion = new HashMap<>();
        List<Map<String, Object>> detalles = new ArrayList<>();
        boolean suficiente = true;
        
        for (Ingrediente ing : coctel.getIngredientes()) {
            if (ing.esOpcional()) continue;
            
            Insumo insumo = insumoRepository.findById(ing.getInsumoId()).orElse(null);
            if (insumo != null) {
                double cantidadNecesaria = ing.getCantidad() * cantidadPreparaciones;
                Map<String, Object> detalle = new HashMap<>();
                detalle.put("insumo", insumo.getNombre());
                detalle.put("cantidadNecesaria", cantidadNecesaria);
                detalle.put("cantidadDisponible", insumo.getCantidadDisponible());
                detalle.put("suficiente", insumo.disponible(cantidadNecesaria));
                
                if (!insumo.disponible(cantidadNecesaria)) {
                    suficiente = false;
                    detalle.put("faltante", cantidadNecesaria - insumo.getCantidadDisponible());
                }
                
                detalles.add(detalle);
            }
        }
        
        proyeccion.put("coctel", coctel.getNombre());
        proyeccion.put("cantidadPreparaciones", cantidadPreparaciones);
        proyeccion.put("suficiente", suficiente);
        proyeccion.put("detalles", detalles);
        
        return proyeccion;
    }
}