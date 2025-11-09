package com.barmanager.repositories;

import com.barmanager.models.Bartender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BartenderRepository extends JpaRepository<Bartender, Integer> {
    
    Optional<Bartender> findByUsuario(String usuario);
    
    Optional<Bartender> findByEmail(String email);
    
    List<Bartender> findByEspecialidad(String especialidad);
    
    @Query("SELECT b FROM Bartender b WHERE b.activo = true ORDER BY b.coctelesPreparados DESC")
    List<Bartender> findMasProductivos();
    
    List<Bartender> findByActivo(boolean activo);
}