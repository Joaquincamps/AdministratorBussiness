package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.producto.DtoCrearProducto;
import com.example.AdministratorBussiness.modelo.Producto;
import com.example.AdministratorBussiness.modelo.ProductoHistorial;
import com.example.AdministratorBussiness.modelo.Proveedor;
import com.example.AdministratorBussiness.opEnum.Operacion;
import com.example.AdministratorBussiness.repositorio.ProductoHistorialRepository;
import com.example.AdministratorBussiness.repositorio.ProductoRepositorio;
import com.example.AdministratorBussiness.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Autowired
    private ProductoHistorialRepository productoHistorialRepository;

    private List<ProductoHistorial> listaProductoHistorial = new ArrayList<>();

    public List<ProductoHistorial> getListaProductoHistorial() {
        return productoHistorialRepository.findAll();
    }

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

    public void reducirStock(Long id, int cantidad) {
        Producto producto = productoRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );
        int stockActual = producto.getStock() - cantidad;
        producto.setStock(stockActual);
        productoRepositorio.save(producto);

        ProductoHistorial productoHistorial = new ProductoHistorial();
        productoHistorial.setProducto(producto);
        productoHistorial.setCantidad(cantidad);
        productoHistorial.setOperacion(Operacion.SALIDA);
        productoHistorial.setFecha(LocalDateTime.now());
        productoHistorial.setStockFinal(producto.getStock());
        productoHistorialRepository.save(productoHistorial);
    }

    public void aumentarStock(Long id, int cantidad) {
        Producto producto = productoRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );
        int stockActual = producto.getStock() + cantidad;
        producto.setStock(stockActual);
        productoRepositorio.save(producto);

        ProductoHistorial productoHistorial = new ProductoHistorial();
        productoHistorial.setProducto(producto);
        productoHistorial.setCantidad(cantidad);
        productoHistorial.setOperacion(Operacion.ENTRADA);
        productoHistorial.setFecha(LocalDateTime.now());
        productoHistorial.setStockFinal(producto.getStock());
        productoHistorialRepository.save(productoHistorial);
    }

}
