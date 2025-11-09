package com.barmanager.controllers;

import com.barmanager.models.Sustitucion;
import com.barmanager.services.SustitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sustituciones")
@CrossOrigin(origins = "*")
public class SustitucionController {
    
    @Autowired
    private SustitucionService sustitucionService;
    
    /*
    GET /api/sustituciones/insumo/{insumoId}     Obtiene sustituciones disponibles para un insumo
    */
    @GetMapping("/insumo/{insumoId}")
    @PreAuthorize("hasAnyRole('BARTENDER', 'ADMINISTRADOR')")
    public ResponseEntity<List<Sustitucion>> obtenerSustituciones(@PathVariable int insumoId) {
        return ResponseEntity.ok(sustitucionService.obtenerSustitucionesDeInsumo(insumoId));
    }
    
    /*
    POST /api/sustituciones      Registra una nueva sustitucion
    */
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Sustitucion> registrarSustitucion(@RequestBody Sustitucion sustitucion) {
        return ResponseEntity.ok(sustitucionService.registrarSustitucion(sustitucion));
    }
    
    /*
    POST /api/sustituciones/auto-generar/{insumoId}     Genera sustituciones automáticas para un insumo
    */
    @PostMapping("/auto-generar/{insumoId}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<Sustitucion>> generarAutomaticas(@PathVariable int insumoId) {
        return ResponseEntity.ok(sustitucionService.generarSustitucionesAutomaticas(insumoId));
    }
    
    /**
    PUT /api/sustituciones/{id}     Actualiza una sustitucion existente
    */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Sustitucion> actualizarSustitucion(
            @PathVariable int id,
            @RequestBody Sustitucion sustitucion) {
        return ResponseEntity.ok(sustitucionService.actualizarSustitucion(id, sustitucion));
    }
    
    /*
    DELETE /api/sustituciones/{id}.       Elimina una sustitucion
    */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> eliminarSustitucion(@PathVariable int id) {
        sustitucionService.eliminarSustitucion(id);
        return ResponseEntity.ok().body("Sustitucion eliminada exitosamente");
    }
}