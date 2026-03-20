package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String contacto;

    private int telefono;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;

    //metodos helpers
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setProveedor(this);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
        producto.setProveedor(null);
    }

    public Proveedor(String nombre, String contacto, int telefono, String email) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.productos = new ArrayList<>();
    }

}