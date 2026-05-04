package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.RegistrarServicio;
import com.example.AdministratorBussiness.modelo.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    Servicio findBytipoServicio(String nombre);

    @Query("SELECT SUM(r.precio) FROM RegistrarServicio r WHERE DATE(r.fecha) =:fecha")
    Double calcularTotalPorDiaEspecifico(@Param("fecha")
                                         LocalDate fecha);

    @Query("SELECT r FROM RegistrarServicio r WHERE DATE(r.fecha) =:fecha")
    List<RegistrarServicio> listarServiciosPorDia(@Param("fecha") LocalDate fecha);

    @Query("SELECT SUM(r.precio) FROM RegistrarServicio r WHERE r.servicio.id =:servicio")
    Double calcularTotalPorServicio(@Param("servicio") Long servicio);
}
