package com.barmanager.services;

import com.barmanager.models.*;
import com.barmanager.dto.*;
import com.barmanager.repositories.*;
import com.barmanager.helpers.*;
import com.barmanager.exceptions.InsumoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoctelService {
    
    @Autowired
    private CoctelRepository coctelRepository;
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    @Autowired
    private IngredienteRepository ingredienteRepository;
    
    @Autowired
    private BartenderRepository bartenderRepository;
    
    @Autowired
    private CoctelHelper coctelHelper;
    
    @Autowired
    private SustitucionHelper sustitucionHelper;
    
    // @Autowired
    // private SustitucionRepository sustitucionRepository;
    
    // cocteles disponibles con analisis avanzado

    public List<CoctelDisponibilidadDTO> obtenerCoctelesConDisponibilidad(
            boolean soloDisponibles,
            boolean incluirConSustituciones) {
        
        List<Coctel> todosCocteles = coctelRepository.findAll();
        
        return coctelHelper.filtrarCoctelesAvanzado(
            todosCocteles, 
            soloDisponibles, 
            incluirConSustituciones, 
            null
        );
    }
    

    // receta detallada con informacion
    public CoctelDetalladoDTO obtenerRecetaCompleta(int coctelId) {
        Coctel coctel = coctelRepository.findById(coctelId)
            .orElseThrow(() -> new RuntimeException("Coctel no encontrado"));
        
        return coctelHelper.obtenerRecetaDetallada(coctel);
    }
    
    // coctel con soporte para sustituciones

    @Transactional
    public Map<String, Object> prepararCoctelConSustituciones(
            int coctelId, 
            int bartenderId,
            boolean permitirSustituciones) throws InsumoInsuficienteException {
        
        Coctel coctel = coctelRepository.findById(coctelId)
            .orElseThrow(() -> new RuntimeException("Coctel no encontrado"));
        
        List<Ingrediente> ingredientes = ingredienteRepository.findByCoctelId(coctelId);
        List<Map<String, String>> sustitucionesUsadas = new ArrayList<>();
        
        // verificar y preparar cada ingrediente
        for (Ingrediente ing : ingredientes) {
            if (ing.esOpcional()) continue;
            
            Insumo insumo = insumoRepository.findById(ing.getInsumoId())
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
            
            if (insumo.disponible(ing.getCantidad())) {
                // usar insumo original
                insumo.descontar(ing.getCantidad());
                insumoRepository.save(insumo);
            } else if (permitirSustituciones) {
                // buscar la  sustitucion
                Optional<Sustitucion> sustitucion = sustitucionHelper
                    .buscarMejorSustitucion(insumo.getId(), ing.getCantidad());
                
                if (sustitucion.isPresent()) {
                    Sustitucion sust = sustitucion.get();
                    double cantidadNecesaria = ing.getCantidad() * sust.getFactorConversion();
                    
                    Insumo sustituto = sust.getInsumoSustituto();
                    sustituto.descontar(cantidadNecesaria);
                    insumoRepository.save(sustituto);
                    
                    Map<String, String> sustInfo = new HashMap<>();
                    sustInfo.put("original", insumo.getNombre());
                    sustInfo.put("sustituto", sustituto.getNombre());
                    sustInfo.put("cantidadUsada", String.valueOf(cantidadNecesaria));
                    sustitucionesUsadas.add(sustInfo);
                } else {
                    throw new InsumoInsuficienteException(
                        insumo.getNombre(),
                        insumo.getCantidadDisponible(),
                        ing.getCantidad()
                    );
                }
            } else {
                throw new InsumoInsuficienteException(
                    insumo.getNombre(),
                    insumo.getCantidadDisponible(),
                    ing.getCantidad()
                );
            }
        }
        
        // Registrar preparacion
        coctel.registrarPreparacion();
        coctelRepository.save(coctel);
        
        // Actualizar estadisticas del bartender
        Bartender bartender = bartenderRepository.findById(bartenderId).orElse(null);
        if (bartender != null) {
            bartender.incrementarCoctelesPreparados();
            bartenderRepository.save(bartender);
        }
        
        // Construir respuesta
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("mensaje", "Coctel preparado exitosamente");
        resultado.put("coctel", coctel.getNombre());
        resultado.put("sustitucionesUsadas", sustitucionesUsadas);
        resultado.put("usaSustituciones", !sustitucionesUsadas.isEmpty());
        
        return resultado;
    }
    
    /* busca cocteles por múltiples criterios */
    public List<CoctelDisponibilidadDTO> buscarCocteles(
            String categoria,
            String dificultad,
            Integer tiempoMaximo,
            boolean soloDisponibles) {
        
        List<Coctel> cocteles = coctelRepository.findAll();
        
        // filtrar 
        cocteles = cocteles.stream()
            .filter(c -> categoria == null || 
                c.getCategoria().name().equalsIgnoreCase(categoria))
            .filter(c -> dificultad == null || 
                c.getDificultad().name().equalsIgnoreCase(dificultad))
            .filter(c -> tiempoMaximo == null || 
                (c.getMetodoPreparacion() != null && 
                 c.getMetodoPreparacion().getTiempoEstimadoMinutos() <= tiempoMaximo))
            .collect(Collectors.toList());
        
        return coctelHelper.filtrarCoctelesAvanzado(
            cocteles, 
            soloDisponibles, 
            true, 
            null
        );
    }
    
    /** sugerir cocteles basados en insumos disponibles */
    public List<CoctelDisponibilidadDTO> sugerirCoctelesPorInventario() {
        List<Coctel> todosCocteles = coctelRepository.findAll();
        
        return coctelHelper.filtrarCoctelesAvanzado(
            todosCocteles, 
            false, 
            true, 
            50.0 // al menos 50% de ingredientes que estn disponibles
        );
    }
}