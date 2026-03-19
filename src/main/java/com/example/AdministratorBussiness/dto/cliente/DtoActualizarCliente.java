package com.example.AdministratorBussiness.dto.cliente;

public class DtoActualizarCliente {

    private Long id;

    private int telefono;

    private String email;

    public DtoActualizarCliente() {
    }

    public DtoActualizarCliente(Long id, int telefono, String email) {
        this.id = id;
        this.telefono = telefono;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
