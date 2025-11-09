package com.barmanager.repositories;

import com.barmanager.models.Insumo;
import com.barmanager.models.enums.TipoInsumo;
// import com.barmanager.models.enums.EstadoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Integer> {
    
    List<Insumo> findByTipo(TipoInsumo tipo);
    
    @Query("SELECT i FROM Insumo i WHERE i.cantidadDisponible <= i.cantidadMinima")
    List<Insumo> findInsumosBajoStock();
    
    @Query("SELECT i FROM Insumo i WHERE i.cantidadDisponible <= 0")
    List<Insumo> findInsumosAgotados();
    
    @Query("SELECT i FROM Insumo i WHERE i.fechaVencimiento <= :fecha")
    List<Insumo> findInsumosPorVencer(LocalDate fecha);
    
    List<Insumo> findByProveedorId(int proveedorId);
    
    @Query("SELECT i FROM Insumo i WHERE LOWER(i.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Insumo> buscarPorNombre(String nombre);
}