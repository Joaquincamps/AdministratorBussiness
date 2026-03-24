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

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrabajadorController {

    @Autowired
    private TrabajadorServicio trabajadorServicio;

    @GetMapping("/trabajadores/nuevo")
    public String crearTrabajador(Model model) {
        model.addAttribute("trabajador", new DtoCrearTrabajador());
        return "trabajador-nuevo";
    }

    //agregar un trabajador a mano
    @PostMapping("/trabajadores/nuevo")
    public String agregarTrabajador(@ModelAttribute("trabajador") DtoCrearTrabajador trabajador,
                                    RedirectAttributes redirectAttributes) {
        try {
            trabajadorServicio.agregarTrabajador(trabajador);
            redirectAttributes.addFlashAttribute("mensajeExito", "Trabajador agregado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/trabajadores";
    }

    //mostrar la vista
    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model model) {
        List<Trabajador> lista = trabajadorServicio.listarTrabajadores();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        model.addAttribute("trabajadores", lista);
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
    public String importarTrabajadoresPorCsv(@RequestParam("file") MultipartFile ficheroCsv,
                                             RedirectAttributes redirectAttributes) {
        if (ficheroCsv.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensaje", "El fichero está vacío");
            return "redirect:/trabajadores";
        }
        trabajadorServicio.importarTrabajadoresCsv(ficheroCsv);

        // Añadir mensaje de éxito opcional
        redirectAttributes.addFlashAttribute("mensajeExito", "CSV importado correctamente");
        return "redirect:/trabajadores";
    }
}
