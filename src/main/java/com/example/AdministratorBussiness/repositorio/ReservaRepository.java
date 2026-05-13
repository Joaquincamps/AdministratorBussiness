package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r JOIN r.trabajador t WHERE t.id = :id")
    List<Reserva> listarReservasPorTrabajador(@Param("id") Long id);
}
