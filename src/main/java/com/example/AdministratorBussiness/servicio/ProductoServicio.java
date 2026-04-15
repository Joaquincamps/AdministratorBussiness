package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.producto.DtoCrearProducto;
import com.example.AdministratorBussiness.modelo.Producto;
import com.example.AdministratorBussiness.modelo.Proveedor;
import com.example.AdministratorBussiness.repositorio.ProductoRepositorio;
import com.example.AdministratorBussiness.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public void registrarProducto(DtoCrearProducto crearProducto) {
        Producto producto = new Producto();
        producto.setNombre(crearProducto.getNombre());
        producto.setStock(crearProducto.getStock());
        producto.setPrecio(crearProducto.getPrecio());
        Proveedor proveedorBuscado = proveedorRepositorio.getReferenceById(crearProducto.getIdProveedor());
        producto.setProveedor(proveedorBuscado);
        productoRepositorio.save(producto);
    }

    public void controlarStock(Long id) {
        Producto productoBuscar = productoRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("EL producto no éxiste en la base de datos.")
        );
        if (productoBuscar.getStock() < 0) {
            throw new RuntimeException("El stock está en negativo, no se puede usar este producto.");
        }
        if (productoBuscar.getStock() < 20) {
            throw new RuntimeException("El stock peligra, en poco ve pensando en realizar un pedido al proveedor");
        }
    }

    public List<Producto> listarProductos() {
        return productoRepositorio.findAll();
    }

}
