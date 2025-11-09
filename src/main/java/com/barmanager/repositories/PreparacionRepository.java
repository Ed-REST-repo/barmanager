package com.barmanager.repositories;

import com.barmanager.models.Preparacion;
import com.barmanager.models.enums.TipoPreparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreparacionRepository extends JpaRepository<Preparacion, Integer> {
    
    List<Preparacion> findByTipo(TipoPreparacion tipo);
    
    List<Preparacion> findByTiempoEstimadoMinutosLessThanEqual(int minutos);
}