package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.producto.DtoCrearProducto;
import com.example.AdministratorBussiness.modelo.Producto;
import com.example.AdministratorBussiness.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @PostMapping("/productos")
    public String registrarProducto(@ModelAttribute("productos") DtoCrearProducto crearProducto, Model model) {
        try {
            productoServicio.registrarProducto(crearProducto);
            model.addAttribute("mensaje", "Producto agregado");
            model.addAttribute("producto", new Producto());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("producto", new Producto());
        }
        return "productos";
    }


}
