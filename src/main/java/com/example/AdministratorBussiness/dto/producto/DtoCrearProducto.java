package com.example.AdministratorBussiness.dto.producto;

public class DtoCrearProducto {

    private String nombre;

    private int stock;

    private double precio;

    private Long idProveedor;

    public DtoCrearProducto() {
    }

    public DtoCrearProducto(String nombre, int stock, double precio, Long idProveedor) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }
}
