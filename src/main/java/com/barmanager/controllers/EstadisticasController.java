package com.barmanager.controllers;

import com.barmanager.services.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v2/estadisticas")
@CrossOrigin(origins = "*")
public class EstadisticasController {
    
    @Autowired
    private EstadisticasService estadisticasService;
    
    // GET /api/v2/estadisticas/cocteles-mas-preparados         Top 10 preparados
    @GetMapping("/cocteles-mas-preparados")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<List<Map<String, Object>>> obtenerCoctelesMasPreparados(
            @RequestParam(defaultValue = "10") int limite) {
        return ResponseEntity.ok(estadisticasService.obtenerCoctelesMasPreparados(limite));
    }
    
    // GET /api/v2/estadisticas/insumos-mas-utilizados
    @GetMapping("/insumos-mas-utilizados")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<List<Map<String, Object>>> obtenerInsumosMasUtilizados(
            @RequestParam(defaultValue = "10") int limite) {
        return ResponseEntity.ok(estadisticasService.obtenerInsumosMasUtilizados(limite));
    }
    
    //cGET /api/v2/estadisticas/preparaciones-por-dificultad
    @GetMapping("/preparaciones-por-dificultad")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<List<Map<String, Object>>> obtenerPreparacionesPorDificultad() {
        return ResponseEntity.ok(estadisticasService.obtenerPreparacionesPorDificultad());
    }
    
    // GET /api/v2/estadisticas/resumen-general
    @GetMapping("/resumen-general")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> obtenerResumenGeneral() {
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("coctelesMasPreparados", estadisticasService.obtenerCoctelesMasPreparados(10));
        resumen.put("insumosMasUtilizados", estadisticasService.obtenerInsumosMasUtilizados(10));
        resumen.put("preparacionesPorDificultad", estadisticasService.obtenerPreparacionesPorDificultad());
        resumen.put("totalPreparaciones", estadisticasService.obtenerTotalPreparaciones());
        return ResponseEntity.ok(resumen);
    }
}