package com.barmanager.repositories;

import com.barmanager.models.Sustitucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SustitucionRepository extends JpaRepository<Sustitucion, Integer> {
    
    List<Sustitucion> findByInsumoOriginalId(int insumoOriginalId);
    
    @Query("SELECT s FROM Sustitucion s WHERE s.insumoOriginal.id = :insumoId " +
           "ORDER BY s.calidadSustitucion DESC")
    List<Sustitucion> findMejoresSustitutos(int insumoId);
    
    @Query("SELECT s FROM Sustitucion s WHERE s.insumoOriginal.id = :insumoId " +
           "AND s.insumoSustituto.cantidadDisponible >= :cantidadRequerida " +
           "ORDER BY s.calidadSustitucion DESC")
    List<Sustitucion> findSustitutosDisponibles(int insumoId, double cantidadRequerida);
}