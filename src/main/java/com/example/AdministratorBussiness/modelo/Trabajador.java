package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre,apellido,puesto;

    @Column(unique = true)
    private String email;

    private double salario;

    private String horaEntrada;

    private String horaSalida;

    private boolean activo;

    private boolean deVacaciones;

    private boolean deLibranza;

    public Trabajador() {
    }

    public Trabajador(Long id, String nombre, String apellido, String puesto, String email, double salario,
                      String horaEntrada,
                      String horaSalida, boolean activo, boolean deVacaciones, boolean deLibranza) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.email = email;
        this.salario = salario;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.activo = activo;
        this.deVacaciones = deVacaciones;
        this.deLibranza = deLibranza;
    }
}
