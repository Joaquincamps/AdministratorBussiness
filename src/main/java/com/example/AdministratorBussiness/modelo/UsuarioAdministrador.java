package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UsuarioAdministrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String pass;

    public UsuarioAdministrador(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;
    }
}
