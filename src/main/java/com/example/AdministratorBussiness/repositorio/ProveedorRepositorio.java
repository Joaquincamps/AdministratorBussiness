package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, Long> {

    boolean existsByEmail(String email);
}
