package com.example.AdministratorBussiness.dto.cliente;

public class DtoActualizarCliente{

    private Long id;

    private int telefono;

    private String email;


    public DtoActualizarCliente() {
    }


    public DtoActualizarCliente(int telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}