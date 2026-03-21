package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
