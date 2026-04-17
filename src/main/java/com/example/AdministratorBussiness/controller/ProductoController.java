package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.producto.DtoCrearProducto;
import com.example.AdministratorBussiness.modelo.Producto;
import com.example.AdministratorBussiness.modelo.ProductoHistorial;
import com.example.AdministratorBussiness.modelo.Proveedor;
import com.example.AdministratorBussiness.servicio.ProductoServicio;
import com.example.AdministratorBussiness.servicio.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping("/productos")
    public String verProductos(Model model) {
        List<Producto> lista = productoServicio.listarProductos();
        List<Proveedor> listaProveedores = proveedorServicio.consultarProveedores();
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        if (listaProveedores.isEmpty()) {
            listaProveedores = new ArrayList<>();
        }
        Producto producto = new Producto();
        producto.setProveedor(new Proveedor());
        model.addAttribute("productos", lista);
        model.addAttribute("producto", producto);
        model.addAttribute("proveedores", listaProveedores);
        return "productos";
    }

    @PostMapping("/productos")
    public String insertarProducto(RedirectAttributes redirectAttributes,
                                   @ModelAttribute("producto") DtoCrearProducto dtoCrearProducto) {
        try {
            productoServicio.registrarProducto(dtoCrearProducto);
            redirectAttributes.addFlashAttribute("mensaje", "Producto insertado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al insertar producto");
        }
        return "redirect:/productos";
    }

    @PostMapping("/productos/reducir-stock/{id}")
    public String reducirStock(RedirectAttributes redirectAttributes, int cantidad, @PathVariable Long id) {
        try {
            productoServicio.reducirStock(id, cantidad);
            redirectAttributes.addFlashAttribute("mensaje", "Stock reducido correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Hubo un problema al reducir stock");
        }
        return "redirect:/productos";
    }

    @PostMapping("/productos/aumentar-stock/{id}")
    public String aumentarStock(RedirectAttributes redirectAttributes, int cantidad, @PathVariable Long id) {
        try {
            productoServicio.aumentarStock(id, cantidad);
            redirectAttributes.addFlashAttribute("mensaje", "Stock aumentado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Hubo un problema al reducir stock");
        }
        return "redirect:/productos";
    }

    @GetMapping("/productos/historial/stock")
    public String verHistorialStock(Model model) {
        List<ProductoHistorial> lista = productoServicio.getListaProductoHistorial();
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        model.addAttribute("historial", lista);
        return "historialStock";
    }

}
