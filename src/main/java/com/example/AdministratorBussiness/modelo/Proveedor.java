package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Proveedor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String contacto;

    private int telefono;

    private String emai;
    

    public Proveedor() {
    }

    public Proveedor(String nombre, String contacto, int telefono, String emai) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.emai = emai;
    }

}