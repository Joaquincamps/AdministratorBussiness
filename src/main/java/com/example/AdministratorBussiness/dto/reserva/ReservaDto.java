package com.example.AdministratorBussiness.dto.reserva;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDto {

    private LocalDate fecha;

    private LocalTime horaInicio, horaFin;

    private Long clienteId;

    private Long trabajadorId;

    public ReservaDto() {
    }

    public ReservaDto(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Long clienteId, Long trabajadorId) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.clienteId = clienteId;
        this.trabajadorId = trabajadorId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Long getIdCliente() {
        return clienteId;
    }

    public void setIdCliente(Long idCliente) {
        this.clienteId = idCliente;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Long trabajadorId) {
        this.trabajadorId = trabajadorId;
    }
}
