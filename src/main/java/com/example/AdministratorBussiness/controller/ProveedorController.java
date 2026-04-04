package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.proveedor.DtoActuProveedor;
import com.example.AdministratorBussiness.dto.proveedor.DtoCrearProveedor;
import com.example.AdministratorBussiness.modelo.Cliente;
import com.example.AdministratorBussiness.modelo.Proveedor;
import com.example.AdministratorBussiness.servicio.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping("/proveedores/nuevo")
    public String crearProveedor(Model model) {
        model.addAttribute("proveedor", new DtoCrearProveedor());
        return "crear-proveedor";
    }

    @PostMapping("/proveedores/nuevo")
    public String agregarProveedor(@ModelAttribute("proveedor") DtoCrearProveedor crearProveedor,
                                   RedirectAttributes redirectAttributes) {
        try {
            proveedorServicio.registrarProveedor(crearProveedor);
            redirectAttributes.addFlashAttribute("mensaje", "Proveedor agregado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores")
    public String listarProveeodores(Model model) {
        List<Proveedor> lista = proveedorServicio.consultarProveedores();
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        model.addAttribute("proveedores", lista);
        return "proveedores";
    }

    @PostMapping("/proveedores/importar")
    public String importarProveedor(@RequestParam("file") MultipartFile fichero,
                                    RedirectAttributes redirectAttributes) {
        if (fichero.isEmpty()) {
            redirectAttributes.addAttribute("mensaje", "El fichero está vacío");
        }
        proveedorServicio.subirProveedorPorFichero(fichero);
        redirectAttributes.addAttribute("mensaje", "Fichero subido con éxito");
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Long id, Model model) {
        try {
            proveedorServicio.eliminarProveedor(id);
            model.addAttribute("mensaje", "Proveedor eliminado con éxito");
        } catch (Exception e) {
            model.addAttribute("Error", "Trabajador no eliminado");
        }
        model.addAttribute("proveedores", proveedorServicio.consultarProveedores());
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores";
    }

    @GetMapping("/proveedores/buscar")
    public String buscarProveedor(@RequestParam("entrada") String entrada, Model model) {
        List<Proveedor> lista = proveedorServicio.buscarProveedorPorParametro(entrada);
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        model.addAttribute("proveedores", lista);
        model.addAttribute("mensaje", "Proveedor encontrado con éxito");
        return "proveedores";
    }

    @GetMapping("/proveedores/editar/{id}")
    public String verEditarProveedor(Model model, @PathVariable Long id) {
        model.addAttribute("proveedor", proveedorServicio.buscarPorId(id));
        return "proveedor-editar.html";
    }

    @PostMapping("/proveedores/editar/{id}")
    public String editarProovedor(RedirectAttributes redirectAttributes,
                                  @PathVariable Long id,
                                  @ModelAttribute("proveedor")DtoActuProveedor) {

    }
}
