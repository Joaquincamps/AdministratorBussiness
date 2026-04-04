package com.example.AdministratorBussiness.dto.proveedor;

public class DtoCrearProveedor {

    private String nombre;

    private String contacto;

    private int telefono;

    private String email;

    public DtoCrearProveedor() {
    }

    public DtoCrearProveedor(String nombre, String contacto, int telefono, String email) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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
