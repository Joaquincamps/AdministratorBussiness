package com.example.AdministratorBussiness.dto.proveedor;

import jakarta.persistence.Column;

public class DtoActuProveedor {

    private String nombreEmpresa;

    private String personaContacto;

    private int telefono;

    private String email;

    public DtoActuProveedor() {
    }

    public DtoActuProveedor(String nombreEmpresa, String personaContacto, int telefono, String email) {
        this.nombreEmpresa = nombreEmpresa;
        this.personaContacto = personaContacto;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
