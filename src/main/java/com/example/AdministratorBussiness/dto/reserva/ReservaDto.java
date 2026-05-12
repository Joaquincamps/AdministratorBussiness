package com.example.AdministratorBussiness.dto.reserva;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaDto {

    private LocalDate fecha;

    private LocalTime horaInicio, horaFin;

    private Long idCliente;

    public ReservaDto() {
    }

    public ReservaDto(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, Long idCliente) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idCliente = idCliente;
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
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
