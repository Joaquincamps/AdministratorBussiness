package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UsuarioAdministrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String password;

    public UsuarioAdministrador(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
}
