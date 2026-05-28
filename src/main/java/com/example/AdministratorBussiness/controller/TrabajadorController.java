package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.trabajador.DtoActualizarTrabajador;
import com.example.AdministratorBussiness.dto.trabajador.DtoCrearTrabajador;
import com.example.AdministratorBussiness.modelo.Trabajador;
import com.example.AdministratorBussiness.servicio.TrabajadorServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String agregarTrabajador(
            @Valid @ModelAttribute("trabajador") DtoCrearTrabajador trabajador,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "Debes rellenar todos los campos obligatorios");
            return "redirect:/trabajadores/nuevo";
        }

        try {
            trabajadorServicio.agregarTrabajador(trabajador);
            redirectAttributes.addFlashAttribute("mensaje",
                    "Trabajador agregado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "Fallo al crear el trabajador");
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

    @PostMapping("/trabajadores/importar")
    public String importarTrabajadoresPorCsv(@RequestParam("file") MultipartFile ficheroCsv,
                                             RedirectAttributes redirectAttributes) {
        if (ficheroCsv.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensaje", "El fichero está vacío");
            return "redirect:/trabajadores";
        }
        try {
            trabajadorServicio.importarTrabajadoresCsv(ficheroCsv);
            redirectAttributes.addFlashAttribute("mensaje", "CSV importado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Fallo al importar el fichero");
        }

        return "redirect:/trabajadores";
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

    @GetMapping("/trabajadores/editar/{id}")
    public String obtenerDatosTrabajador(Model model, @PathVariable Long id) {
        model.addAttribute("trabajador", trabajadorServicio.obtenerTrabajador(id));
        return "trabajador-editar";
    }

    @PostMapping("/trabajadores/actualizar/{id}")
    public String actualizarValoresTrabajador(
            @Valid @ModelAttribute("trabajador") DtoActualizarTrabajador updateTrabajador,
            BindingResult result,
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Debes rellenar correctamente los campos");
            return "redirect:/trabajadores/editar/" + id;
        }

        try {
            trabajadorServicio.actualizarTrabajador(updateTrabajador, id);

            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    "Trabajador editado con éxito");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "mensaje",
                    e.getMessage());
        }

        return "redirect:/trabajadores";
    }
}
