package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.producto.DtoCrearProducto;
import com.example.AdministratorBussiness.modelo.Producto;
import com.example.AdministratorBussiness.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;


    @GetMapping("/productos")
    public String verProductos(Model model) {
        List<Producto> lista = productoServicio.listarProductos();
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        model.addAttribute("productos", lista);
        model.addAttribute("producto", new Producto());
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


}
