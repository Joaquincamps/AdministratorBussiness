package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.RegistrarServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrarServicioRepository extends JpaRepository<RegistrarServicio,Long> {
}
