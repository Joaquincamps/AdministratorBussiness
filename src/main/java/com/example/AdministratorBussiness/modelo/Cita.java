package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Servicio servicio;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
