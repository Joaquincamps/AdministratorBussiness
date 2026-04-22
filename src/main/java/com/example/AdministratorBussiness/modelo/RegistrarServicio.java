package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RegistrarServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    private double precio;

    private LocalDateTime fecha;

    public RegistrarServicio() {
    }

    public RegistrarServicio(Servicio servicio, double precio, LocalDateTime fecha) {
        this.servicio = servicio;
        this.precio = precio;
        this.fecha = fecha;
    }
}
