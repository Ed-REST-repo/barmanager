package com.barmanager.controllers;

import com.barmanager.models.Insumo;
import com.barmanager.services.InsumoService;
import com.barmanager.dto.ReposicionInsumoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoController {
    
    @Autowired
    private InsumoService insumoService;
    
    // Listar todos los insumos
    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<Insumo>> listarTodos() {
        return ResponseEntity.ok(insumoService.obtenerTodos());
    }
    
    // Obtener insumo por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Insumo> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(insumoService.obtenerPorId(id));
    }
    
    // Registrar nuevo insumo
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Insumo> registrarInsumo(@RequestBody Insumo insumo) {
        return ResponseEntity.ok(insumoService.registrar(insumo));
    }
    
    // Actualizar insumo
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Insumo> actualizarInsumo(
            @PathVariable int id, 
            @RequestBody Insumo insumo) {
        return ResponseEntity.ok(insumoService.actualizar(id, insumo));
    }
    
    // Reponer stock de insumo
    @PostMapping("/{id}/reponer")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, String>> reponerStock(
            @PathVariable int id,
            @RequestBody ReposicionInsumoRequest request) {
        insumoService.reponer(id, request.getCantidad());
        return ResponseEntity.ok(Map.of("message", "Stock repuesto exitosamente"));
    }
    
    // Insumos que requieren reposición
    @GetMapping("/bajo-stock")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<Insumo>> obtenerBajoStock() {
        return ResponseEntity.ok(insumoService.obtenerInsumosReposicion());
    }
    
    // Insumos por tipo
    @GetMapping("/tipo/{tipo}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<Insumo>> obtenerPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(insumoService.obtenerPorTipo(tipo));
    }
}