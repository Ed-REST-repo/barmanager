package com.barmanager.repositories;

import com.barmanager.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    
    List<Ingrediente> findByCoctelId(int coctelId);
    
    List<Ingrediente> findByInsumoId(int insumoId);
    
    @Query("SELECT i FROM Ingrediente i WHERE i.coctelId = :coctelId ORDER BY i.orden")
    List<Ingrediente> findByCoctelIdOrdenado(int coctelId);
    
    @Query("SELECT i FROM Ingrediente i WHERE i.esOpcional = false AND i.coctelId = :coctelId")
    List<Ingrediente> findIngredientesObligatorios(int coctelId);
}