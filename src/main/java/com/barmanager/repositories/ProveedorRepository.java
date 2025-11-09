package com.barmanager.repositories;

import com.barmanager.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    
    List<Proveedor> findByActivo(boolean activo);
    
    @Query("SELECT p FROM Proveedor p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Proveedor> buscarPorNombre(String nombre);
    
    List<Proveedor> findByDireccionCiudad(String ciudad);
}