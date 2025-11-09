package com.barmanager.controllers;

import com.barmanager.dto.*;
import com.barmanager.services.CoctelService;
import com.barmanager.helpers.InventarioHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v2/cocteles")
@CrossOrigin(origins = "*")
public class CoctelController {
    
    @Autowired
    private CoctelService coctelService;
    
    @Autowired
    private InventarioHelper inventarioHelper;
    
    /* 
    GET /api/v2/cocteles/disponibles --> Obtiene cocteles con analisis de disponibilidad
    */
    @GetMapping("/disponibles")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<List<CoctelDisponibilidadDTO>> obtenerCoctelesDisponibles(
            @RequestParam(defaultValue = "false") boolean soloDisponibles,
            @RequestParam(defaultValue = "true") boolean incluirConSustituciones) {
        
        return ResponseEntity.ok(
            coctelService.obtenerCoctelesConDisponibilidad(
                soloDisponibles, 
                incluirConSustituciones
            )
        );
    }
    
    /*
    GET /api/v2/cocteles/{id}/receta-detallada.          Obtiene receta completa con toda la informacion
    */
    @GetMapping("/{id}/receta-detallada")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<CoctelDetalladoDTO> obtenerRecetaDetallada(@PathVariable int id) {
        return ResponseEntity.ok(coctelService.obtenerRecetaCompleta(id));
    }
    
    /*
    POST /api/v2/cocteles/{id}/preparar         Prepara coctel con soporte para sustituciones
     */
    @PostMapping("/{id}/preparar")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<?> prepararCoctel(
            @PathVariable int id,
            @RequestBody Map<String, Object> request) {
        
        try {
            int bartenderId = (int) request.get("bartenderId");
            boolean permitirSustituciones = request.containsKey("permitirSustituciones") ? 
                (boolean) request.get("permitirSustituciones") : true;
            
            Map<String, Object> resultado = coctelService
                .prepararCoctelConSustituciones(id, bartenderId, permitirSustituciones);
            
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }
    
    /*
    GET /api/v2/cocteles/buscar Busqueda avanzada de cocteles
     */
    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<List<CoctelDisponibilidadDTO>> buscarCocteles(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String dificultad,
            @RequestParam(required = false) Integer tiempoMaximo,
            @RequestParam(defaultValue = "false") boolean soloDisponibles) {
        
        return ResponseEntity.ok(
            coctelService.buscarCocteles(categoria, dificultad, tiempoMaximo, soloDisponibles)
        );
    }
    
    /*
    GET /api/v2/cocteles/sugerencias     Sugiere cocteles basados en inventario disponible
     */
    @GetMapping("/sugerencias")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<List<CoctelDisponibilidadDTO>> obtenerSugerencias() {
        return ResponseEntity.ok(coctelService.sugerirCoctelesPorInventario());
    }
    
    /*
    POST /api/v2/cocteles/{id}/proyectar-consumo   Proyecta consumo de ingredientes para múltiples preparaciones
    */
    @PostMapping("/{id}/proyectar-consumo")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<?> proyectarConsumo(
            @PathVariable int id,
            @RequestParam int cantidadPreparaciones) {
        
        try {
            var coctel = coctelService.obtenerRecetaCompleta(id).getCoctel();
            var proyeccion = inventarioHelper.proyectarConsumo(coctel, cantidadPreparaciones);
            return ResponseEntity.ok(proyeccion);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", e.getMessage()));
        }
    }
}