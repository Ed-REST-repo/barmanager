package com.barmanager.services;

import com.barmanager.models.*;
import com.barmanager.repositories.*;
import com.barmanager.helpers.SustitucionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class SustitucionService {
    
    @Autowired
    private SustitucionRepository sustitucionRepository;
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    @Autowired
    private SustitucionHelper sustitucionHelper;
    
    /** Registrar una nueva sustitucion */
    @Transactional
    public Sustitucion registrarSustitucion(Sustitucion sustitucion) {
        Objects.requireNonNull(sustitucion, "La sustitucion no puede ser null");
        Objects.requireNonNull(sustitucion.getInsumoOriginal(), "El insumo original no puede ser null");
        Objects.requireNonNull(sustitucion.getInsumoSustituto(), "El insumo sustituto no puede ser null");
        
        if (sustitucion.getInsumoOriginal().getId() == sustitucion.getInsumoSustituto().getId()) {
            throw new IllegalArgumentException("El insumo original y sustituto no pueden ser el mismo");
        }
        
        return sustitucionRepository.save(sustitucion);
    }
    
    /** Obtiene todas las sustituciones de un insumo*/
    public List<Sustitucion> obtenerSustitucionesDeInsumo(int insumoId) {
        if (insumoId <= 0) {
            throw new IllegalArgumentException("El ID del insumo debe ser mayor a 0");
        }
        
        List<Sustitucion> resultado = sustitucionRepository.findByInsumoOriginalId(insumoId);
        return resultado != null ? resultado : Collections.emptyList();
    }
    
    /**Generar sustituciones automáticas para un insumo*/
    @Transactional
    public List<Sustitucion> generarSustitucionesAutomaticas(int insumoId) {
        if (insumoId <= 0) {
            throw new IllegalArgumentException("El ID del insumo debe ser mayor a 0");
        }
        
        Insumo insumo = insumoRepository.findById(insumoId)
            .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
        
        List<Sustitucion> sugerencias = sustitucionHelper
            .sugerirSustitucionesAutomaticas(insumo);
        
        // null y lista vacia
        if (sugerencias == null || sugerencias.isEmpty()) {
            return Collections.emptyList();
        }
        
        // filtrar  null antes de guardar
        List<Sustitucion> sugerenciasValidas = new ArrayList<>();
        for (Sustitucion s : sugerencias) {
            if (s != null) {
                sugerenciasValidas.add(s);
            }
        }
        
        if (sugerenciasValidas.isEmpty()) {
            return Collections.emptyList();
        }
        
        List<Sustitucion> guardadas = sustitucionRepository.saveAll(sugerenciasValidas);
        return guardadas != null ? guardadas : Collections.emptyList();
    }
    
    /** Actualiza una sustitucion existente*/
    @Transactional
    public Sustitucion actualizarSustitucion(int id, Sustitucion sustitucion) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }
        
        Objects.requireNonNull(sustitucion, "La sustitucion no puede ser null");
        
        Sustitucion existente = sustitucionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sustitucion no encontrada"));
        
        // validar factor de conversin
        if (sustitucion.getFactorConversion() <= 0) {
            throw new IllegalArgumentException("El factor de conversión debe ser mayor a 0");
        }
        
        // validar calidad 1-5
        if (sustitucion.getCalidadSustitucion() < 1 || sustitucion.getCalidadSustitucion() > 5) {
            throw new IllegalArgumentException("La calidad debe estar entre 1 y 5");
        }
        
        existente.setFactorConversion(sustitucion.getFactorConversion());
        existente.setCalidadSustitucion(sustitucion.getCalidadSustitucion());
        existente.setNotas(sustitucion.getNotas());
        
        return sustitucionRepository.save(existente);
    }
    
    /** Elimina una sustitucion */
    @Transactional
    public void eliminarSustitucion(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }
        
        if (!sustitucionRepository.existsById(id)) {
            throw new RuntimeException("Sustitucion no encontrada con ID: " + id);
        }
        
        sustitucionRepository.deleteById(id);
    }
}