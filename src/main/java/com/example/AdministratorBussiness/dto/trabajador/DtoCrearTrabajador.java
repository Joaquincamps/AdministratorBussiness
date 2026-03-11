package com.example.AdministratorBussiness.dto.trabajador;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class DtoCrearTrabajador {

    private String nombre, apellido, puesto;

    private String email;

    private double salario;

    private LocalDate fechaEntrada;

    private boolean activo;

    private boolean deVacaciones;

    private boolean deLibranza;

    public DtoCrearTrabajador() {
    }

    public DtoCrearTrabajador(String nombre, String apellido, String puesto, String email, double salario, LocalDate fechaEntrada, boolean activo, boolean deVacaciones, boolean deLibranza) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.email = email;
        this.salario = salario;
        this.fechaEntrada = fechaEntrada;
        this.activo = activo;
        this.deVacaciones = deVacaciones;
        this.deLibranza = deLibranza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public boolean isDeVacaciones() {
        return deVacaciones;
    }

    public void setDeVacaciones(boolean deVacaciones) {
        this.deVacaciones = deVacaciones;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isDeLibranza() {
        return deLibranza;
    }

    public void setDeLibranza(boolean deLibranza) {
        this.deLibranza = deLibranza;
    }
}
