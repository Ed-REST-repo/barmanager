package com.barmanager.repositories;

import com.barmanager.models.UnidadMedida;
import com.barmanager.models.enums.TipoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Integer> {
    
    Optional<UnidadMedida> findByNombre(String nombre);
    
    Optional<UnidadMedida> findByAbreviatura(String abreviatura);
    
    List<UnidadMedida> findByTipo(TipoUnidad tipo);
}