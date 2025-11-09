package com.barmanager.services;

import com.barmanager.models.Insumo;
import com.barmanager.models.enums.TipoInsumo;
import com.barmanager.repositories.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsumoService {
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    public List<Insumo> obtenerTodos() {
        return insumoRepository.findAll();
    }
    
    public Insumo obtenerPorId(int id) {
        return insumoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
    }
    
    @Transactional
    public Insumo registrar(Insumo insumo) {
        if (insumo == null) {
            throw new IllegalArgumentException("El insumo no puede ser null");
        }
        return insumoRepository.save(insumo);
    }

    @Transactional
    public Insumo actualizar(int id, Insumo insumo) {
        if (insumo == null) {
            throw new IllegalArgumentException("El insumo no puede ser null");
        }
        
        Insumo existente = obtenerPorId(id);
        existente.setNombre(insumo.getNombre());
        existente.setTipo(insumo.getTipo());
        existente.setCantidadDisponible(insumo.getCantidadDisponible());
        existente.setCantidadMinima(insumo.getCantidadMinima());
        existente.setPrecio(insumo.getPrecio());
        existente.setProveedor(insumo.getProveedor());
        existente.setFechaVencimiento(insumo.getFechaVencimiento());
        return insumoRepository.save(existente);
    }
    
    @Transactional
    public void reponer(int id, double cantidad) {
        Insumo insumo = obtenerPorId(id);
        insumo.reponer(cantidad);
        insumoRepository.save(insumo);
    }
    
    public List<Insumo> obtenerInsumosReposicion() {
        return insumoRepository.findAll().stream()
            .filter(Insumo::requiereReposicion)
            .collect(Collectors.toList());
    }
    
public List<Insumo> obtenerPorTipo(String tipo) {
    try {
        TipoInsumo tipoEnum = TipoInsumo.valueOf(tipo.toUpperCase());
        return insumoRepository.findByTipo(tipoEnum);
    } catch (IllegalArgumentException e) {
        // si el tipo no existe retornar lista vacia
        return new ArrayList<>();
    }
}
}