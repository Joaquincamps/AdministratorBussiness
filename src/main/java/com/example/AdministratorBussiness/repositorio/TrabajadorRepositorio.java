package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepositorio extends JpaRepository<Trabajador,Long> {

    boolean existsByEmail(String email);
}
