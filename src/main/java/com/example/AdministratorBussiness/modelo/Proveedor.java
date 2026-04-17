package com.example.AdministratorBussiness.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    public Proveedor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}