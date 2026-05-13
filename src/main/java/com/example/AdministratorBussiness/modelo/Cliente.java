package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int telefono;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Reserva> reservas;

    //metodos helpers
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
        reserva.setCliente(this);
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        reserva.setCliente(null);
    }

    @OneToMany(mappedBy = "cliente")
    private List<Cita> citas;

    //metodos helpers
    public void agregarCita(Cita cita) {
        citas.add(cita);
        cita.setCliente(this);
    }

    public void eliminarCita(Cita cita) {
        citas.remove(cita);
        cita.setCliente(null);
    }

    public Cliente() {
    }

    public Cliente(String nombre, int telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.citas = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }


}
