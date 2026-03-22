package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.trabajador.DtoActualizarTrabajador;
import com.example.AdministratorBussiness.dto.trabajador.DtoCrearTrabajador;
import com.example.AdministratorBussiness.modelo.Trabajador;
import com.example.AdministratorBussiness.servicio.TrabajadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TrabajadorController {

    @Autowired
    private TrabajadorServicio trabajadorServicio;

    @PostMapping("/trabajadores")
    public String agregarTrabajador(@ModelAttribute("trabajador") DtoCrearTrabajador trabajador, Model model) {
        try {
            trabajadorServicio.agregarTrabajador(trabajador);
            model.addAttribute("mensaje", "Trabajador agregado con exito");
            model.addAttribute("trabajador", new Trabajador());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("trabajador", trabajador);
        }

        return "trabajadores";
    }

    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model model) {
        model.addAttribute("trabajadores", trabajadorServicio.listarTrabajadores());
        model.addAttribute("trabajador", new Trabajador());
        return "trabajadores";
    }

    @GetMapping("/trabajadores/eliminar/{id}")
    public String eliminarTrabajador(Model model, @PathVariable Long id) {
        try {
            trabajadorServicio.eliminarTrabajador(id);
            model.addAttribute("mensaje", "Trabajador eliminado con éxito");

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("trabajadores", trabajadorServicio.listarTrabajadores());
        model.addAttribute("trabajador", new Trabajador());
        return "trabajadores";
    }

    @PostMapping("/trabajadores/actualizar")
    public String actualizarValoresTrabajador(DtoActualizarTrabajador updateTrabajador) {
        trabajadorServicio.actualizarTrabajador(updateTrabajador);
        return "trabajadores";
    }

    @PostMapping("/trabajadores/importar")
    public String importarTrabajadoresPorCsv(@RequestParam("file") MultipartFile ficheroCsv
    , RedirectAttributes redirectAttributes) {
        if (ficheroCsv.isEmpty()) {
            redirectAttributes.addAttribute("mensaje","El fichero estaá vacío");
        }
        trabajadorServicio.importarTrabajadoresCsv(ficheroCsv);
        return "redirect:/trabajadores";
    }
}
