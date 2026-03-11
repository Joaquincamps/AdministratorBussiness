package com.example.AdministratorBussiness.dto.trabajador;

import java.time.LocalDate;

public class DtoActualizarTrabajador {

    private Long id;

    private String puesto;

    private double salario;

    private LocalDate fechaSalida;

    private boolean activo, deVacaciones, deLibranza;

    public DtoActualizarTrabajador() {
    }

    public DtoActualizarTrabajador(Long id, String puesto, double salario, LocalDate fechaSalida, boolean activo, boolean deVacaciones, boolean deLibranza) {
        this.id = id;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaSalida = fechaSalida;
        this.activo = activo;
        this.deVacaciones = deVacaciones;
        this.deLibranza = deLibranza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isDeVacaciones() {
        return deVacaciones;
    }

    public void setDeVacaciones(boolean deVacaciones) {
        this.deVacaciones = deVacaciones;
    }

    public boolean isDeLibranza() {
        return deLibranza;
    }

    public void setDeLibranza(boolean deLibranza) {
        this.deLibranza = deLibranza;
    }
}
