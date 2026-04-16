package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEmpresa;

    private String personaContacto;

    private int telefono;

    private boolean activo;

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

    public Proveedor(String nombreEmpresa, String personaContacto, int telefono, boolean activo, String email) {
        this.nombreEmpresa = nombreEmpresa;
        this.personaContacto = personaContacto;
        this.telefono = telefono;
        this.activo = activo;
        this.email = email;
        this.productos = new ArrayList<>();
    }
}