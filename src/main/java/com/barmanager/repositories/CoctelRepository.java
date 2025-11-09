package com.barmanager.repositories;

import com.barmanager.models.Coctel;
import com.barmanager.models.enums.CategoriaCoctel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoctelRepository extends JpaRepository<Coctel, Integer> {
    
    List<Coctel> findByCategoria(CategoriaCoctel categoria);
    
    @Query("SELECT c FROM Coctel c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Coctel> buscarPorNombre(String nombre);
    
    List<Coctel> findByDificultad(String dificultad);
    
    @Query("SELECT c FROM Coctel c ORDER BY c.vecesPreparado DESC")
    List<Coctel> findMasPreparados();
    
    @Query("SELECT c FROM Coctel c ORDER BY c.calificacionPromedio DESC")
    List<Coctel> findMejorCalificados();
}