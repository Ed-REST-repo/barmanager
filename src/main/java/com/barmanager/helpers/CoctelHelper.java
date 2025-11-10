package com.barmanager.helpers;

import com.barmanager.models.*;
import com.barmanager.models.enums.EstadoDisponibilidad;
import com.barmanager.dto.*;
import com.barmanager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CoctelHelper {
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    @Autowired
    private SustitucionRepository sustitucionRepository;
    
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    /* Analiza la disponibilidad de un coctel con el inventario actual */
    public CoctelDisponibilidadDTO analizarDisponibilidad(Coctel coctel) {
        CoctelDisponibilidadDTO dto = new CoctelDisponibilidadDTO();
        dto.setCoctelId(coctel.getId());
        dto.setNombreCoctel(coctel.getNombre());
        dto.setDescripcion(coctel.getDescripcion());
        dto.setCategoria(coctel.getCategoria().getDescripcion());
        dto.setDificultad(coctel.getDificultad().getDescripcion());
        
        List<Ingrediente> ingredientes = ingredienteRepository.findByCoctelId(coctel.getId());
        List<Ingrediente> obligatorios = ingredientes.stream()
            .filter(i -> !i.esOpcional())
            .collect(Collectors.toList());
        
        int disponibles = 0;
        List<String> faltantes = new ArrayList<>();
        boolean tieneSustituciones = false;
        
        for (Ingrediente ing : obligatorios) {
            Insumo insumo = insumoRepository.findById(ing.getInsumoId()).orElse(null);
            
            if (insumo != null && insumo.disponible(ing.getCantidad())) {
                disponibles++;
            } else {
                if (insumo != null) {
                    faltantes.add(insumo.getNombre());
                    // Verificar si hay sustituciones disponibles
                    List<Sustitucion> sust = sustitucionRepository
                        .findSustitutosDisponibles(insumo.getId(), ing.getCantidad());
                    if (!sust.isEmpty()) {
                        tieneSustituciones = true;
                    }
                }
            }
        }
        
        dto.setIngredientesDisponibles(disponibles);
        dto.setIngredientesTotales(obligatorios.size());
        dto.setPorcentajeDisponibilidad(
            obligatorios.isEmpty() ? 0 : (disponibles * 100.0) / obligatorios.size()
        );
        dto.setInsumosFaltantes(faltantes);
        dto.setTieneSustituciones(tieneSustituciones);
        
        // Determinar estado
        if (disponibles == obligatorios.size()) {
            dto.setEstado(EstadoDisponibilidad.DISPONIBLE);
        } else if (tieneSustituciones) {
            dto.setEstado(EstadoDisponibilidad.PREPARABLE_CON_SUSTITUCIONES);
        } else {
            dto.setEstado(EstadoDisponibilidad.NO_DISPONIBLE);
        }
        
        return dto;
    }
    
    /* Obtiene receta detallada con informacion de disponibilidad */
    public CoctelDetalladoDTO obtenerRecetaDetallada(Coctel coctel) {
        CoctelDetalladoDTO dto = new CoctelDetalladoDTO();
        dto.setCoctel(coctel);
        
        // Ingredientes con detalles
        List<Ingrediente> ingredientes = ingredienteRepository.findByCoctelIdOrdenado(coctel.getId());
        List<IngredienteDetalleDTO> detalles = new ArrayList<>();
        boolean preparable = true;
        
        for (Ingrediente ing : ingredientes) {
            IngredienteDetalleDTO detalle = new IngredienteDetalleDTO();
            Insumo insumo = insumoRepository.findById(ing.getInsumoId()).orElse(null);
            
            if (insumo != null) {
                detalle.setNombreInsumo(insumo.getNombre());
                detalle.setCantidad(ing.getCantidad());
                detalle.setUnidad(ing.getUnidad() != null ? ing.getUnidad().getAbreviatura() : "");
                detalle.setDisponible(insumo.disponible(ing.getCantidad()));
                detalle.setCantidadDisponible(insumo.getCantidadDisponible());
                detalle.setEsOpcional(ing.esOpcional());
                detalle.setNotas(ing.getNotas());
                
                if (!detalle.isDisponible() && !detalle.isEsOpcional()) {
                    preparable = false;
                }
            }
            
            detalles.add(detalle);
        }
        
        dto.setIngredientes(detalles);
        dto.setPreparable(preparable);
        
        // Pasos de preparación
        if (coctel.getMetodoPreparacion() != null) {
            dto.setPasos(coctel.getMetodoPreparacion().getPasos());
            dto.setTiempoPreparacion(coctel.getMetodoPreparacion().getTiempoEstimadoMinutos());
        }
        
        if (coctel.getInfoCoctel() != null) {
            dto.setNivelAlcohol(coctel.getInfoCoctel().getNivelAlcohol());
            dto.setNivelCalorico(coctel.getInfoCoctel().getNivelCalorico());
        }
        
        // Costo estimado
        dto.setCostoEstimado(coctel.getCostoTotal());
        
        // Sustituciones sugeridas
        dto.setSustituciones(obtenerSustituciones(coctel));
        
        return dto;
    }
    
    /* Busca sustituciones posibles para ingredintes faltantes */
    public List<SustitucionSugeridaDTO> obtenerSustituciones(Coctel coctel) {
        List<SustitucionSugeridaDTO> sustituciones = new ArrayList<>();
        List<Ingrediente> ingredientes = ingredienteRepository.findByCoctelId(coctel.getId());
        
        for (Ingrediente ing : ingredientes) {
            if (ing.esOpcional()) continue;
            
            Insumo insumo = insumoRepository.findById(ing.getInsumoId()).orElse(null);
            
            // Solo buscar sustituciones si el insumo no esta disponible
            if (insumo != null && !insumo.disponible(ing.getCantidad())) {
                List<Sustitucion> sust = sustitucionRepository.findMejoresSustitutos(insumo.getId());
                
                for (Sustitucion s : sust) {
                    // Verificar si el sustituto está disponible
                    double cantidadNecesaria = ing.getCantidad() * s.getFactorConversion();
                    if (s.getInsumoSustituto().disponible(cantidadNecesaria)) {
                        SustitucionSugeridaDTO dto = new SustitucionSugeridaDTO();
                        dto.setInsumoOriginal(insumo.getNombre());
                        dto.setInsumoSustituto(s.getInsumoSustituto().getNombre());
                        dto.setCantidadNecesaria(cantidadNecesaria);
                        dto.setUnidad(ing.getUnidad() != null ? 
                            ing.getUnidad().getAbreviatura() : "");
                        dto.setCalidadSustitucion(s.getCalidadSustitucion());
                        dto.setNotas(s.getNotas());
                        sustituciones.add(dto);
                        break; // Solo sugerir la mejor sustitucion
                    }
                }
            }
        }
        
        return sustituciones;
    }
    
    /* Filtra cocteles segun criterios avanzados */
    public List<CoctelDisponibilidadDTO> filtrarCoctelesAvanzado(
            List<Coctel> cocteles, 
            boolean soloDisponibles,
            boolean incluirConSustituciones,
            Double porcentajeMinimo) {
        
        return cocteles.stream()
            .map(this::analizarDisponibilidad)
            .filter(dto -> {
                if (soloDisponibles && dto.getEstado() != EstadoDisponibilidad.DISPONIBLE) {
                    return false;
                }
                if (!incluirConSustituciones && 
                    dto.getEstado() == EstadoDisponibilidad.PREPARABLE_CON_SUSTITUCIONES) {
                    return false;
                }
                if (porcentajeMinimo != null && 
                    dto.getPorcentajeDisponibilidad() < porcentajeMinimo) {
                    return false;
                }
                return true;
            })
            .sorted((a, b) -> Double.compare(
                b.getPorcentajeDisponibilidad(), 
                a.getPorcentajeDisponibilidad()
            ))
            .collect(Collectors.toList());
    }
}