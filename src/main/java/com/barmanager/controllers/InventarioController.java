package com.barmanager.controllers;

import com.barmanager.helpers.InventarioHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {
    
    @Autowired
    private InventarioHelper inventarioHelper;
    
    /*
    GET /api/inventario/criticos      Obtiene reporte de insumos críticos
    */
    @GetMapping("/criticos")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> obtenerInsumosCriticos() {
        return ResponseEntity.ok(inventarioHelper.obtenerInsumosCriticos());
    }
    
    /*
    GET /api/inventario/valor-total         Calcula valor total del inventario
    */
    @GetMapping("/valor-total")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> obtenerValorTotal() {
        double valor = inventarioHelper.calcularValorInventario();
        return ResponseEntity.ok(Map.of("valorTotal", valor));
    }
}