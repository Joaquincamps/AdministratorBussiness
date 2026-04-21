package com.example.AdministratorBussiness.dto.servicio;

import com.example.AdministratorBussiness.Enums.opServicio.EnumServicio;

public class DtoServicio {

    private EnumServicio nombre;

    private double precio;

    public DtoServicio() {
    }

    public DtoServicio(EnumServicio nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public EnumServicio getNombre() {
        return nombre;
    }

    public void setNombre(EnumServicio nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
