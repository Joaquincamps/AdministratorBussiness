package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT p FROM Proveedor p WHERE p.nombreEmpresa LIKE %:entrada% OR p.personaContacto LIKE %:entrada%")
    List<Proveedor> findProveedor(@Param("entrada") String entrada);
}
