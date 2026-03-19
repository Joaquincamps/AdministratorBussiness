package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.proveedor.DtoCrearProveedor;
import com.example.AdministratorBussiness.modelo.Proveedor;
import com.example.AdministratorBussiness.servicio.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @PostMapping("/proveedor")
    public String agregarProveedor(@ModelAttribute("proveedor") DtoCrearProveedor crearProveedor, Model model) {
        try {
            proveedorServicio.registrarProveedor(crearProveedor);
            model.addAttribute("mensaje", "Trabajador agregado con éxito");
            model.addAttribute("proveedor", new Proveedor());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("proveedor", crearProveedor);
        }
        return "proveedor";
    }

    @GetMapping("/proveedor")
    public String listarProveeodores(Model model) {
        model.addAttribute("proveedores", proveedorServicio.consultarProveedores());
        model.addAttribute("proveedores", new Proveedor());
        return "proveedor";
    }
}
