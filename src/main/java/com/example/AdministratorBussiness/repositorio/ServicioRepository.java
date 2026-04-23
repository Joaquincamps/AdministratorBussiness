package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    Servicio findBytipoServicio(String nombre);


}
