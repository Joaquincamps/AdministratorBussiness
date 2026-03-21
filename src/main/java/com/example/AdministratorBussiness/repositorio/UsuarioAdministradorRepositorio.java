package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.UsuarioAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioAdministradorRepositorio extends JpaRepository<UsuarioAdministrador,Long> {

    boolean existsByNombre(String nombre);
}
