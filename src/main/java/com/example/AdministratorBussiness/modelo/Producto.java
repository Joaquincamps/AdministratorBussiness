package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int stock;

    private double precio;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }
}
