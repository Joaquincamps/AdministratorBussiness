package com.example.AdministratorBussiness.dto.servicio;

import com.example.AdministratorBussiness.Enums.opServicio.EnumServicio;

public class DtoServicio {

    private String nombre;

    private double precio;

    public DtoServicio() {
    }

    public DtoServicio(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
