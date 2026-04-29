package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.servicio.DtoServicio;
import com.example.AdministratorBussiness.modelo.RegistrarServicio;
import com.example.AdministratorBussiness.modelo.Servicio;
import com.example.AdministratorBussiness.servicio.ServiciosDisponibles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServicioDisponibleController {

    @Autowired
    private ServiciosDisponibles serviciosDisponibles;

    @GetMapping("/servicios")
    public String listarServicios(Model model) {
        List<Servicio> lista = serviciosDisponibles.listarServicios();
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        model.addAttribute("servicios", lista);
        return "servicios";
    }

    @GetMapping("/servicios/nuevo")
    public String vistaCrearServicioNuevo(Model model) {
        model.addAttribute("servicio", new DtoServicio());
        return "crear-servicio";
    }

    @PostMapping("/servicios")
    public String crearServicio(RedirectAttributes redirectAttributes,
                                @ModelAttribute("servicio") DtoServicio dtoServicio) {
        try {
            serviciosDisponibles.insertarServicio(dtoServicio);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio creado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("mensaje", "Hubo un problema al crear el servicio");
        }
        return "redirect:/servicios";
    }

    @PostMapping("/servicios/modificar-precio/{id}")
    public String actualizarPrecio(@PathVariable Long id, double precio, RedirectAttributes
            redirectAttributes) {
        try {
            serviciosDisponibles.actualizarPrecio(id, precio);
            redirectAttributes.addFlashAttribute("mensaje", "Precio actualizado");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Hubo una incidencia al actualizar el precio");
        }
        return "redirect:/servicios";
    }

    @PostMapping("/servicios/registrar/{id}")
    public String registrarServicio(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            serviciosDisponibles.registrarServicio(id);
            redirectAttributes.addFlashAttribute("mensaje", "Servicio registrado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "No se pudo registrar el servicio");
        }
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/ver/registrados")
    public String verServiciosRegistrados(Model model) {
        List<RegistrarServicio> lista = serviciosDisponibles.listarHistorialServicios();
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        model.addAttribute("registros", lista);
        return "historial-servicios";
    }

    @GetMapping("/servicios/historial/total")
    public String calcularTotalPorDiaEspecifico(@RequestParam LocalDate fecha,
                                                Model model) {
        double valorPorFecha = serviciosDisponibles.calcularTotalDeDiaEspecifico(fecha);
        model.addAttribute("total", valorPorFecha);
        return "historial-servicios";
    }

    @GetMapping("/servicios/historial/filtrar")
    public String listarServiciosPorDia(@RequestParam LocalDate fecha,
                                        Model model) {
        List<RegistrarServicio> lista = serviciosDisponibles.listarServicioPorDia(fecha);
        if (lista.isEmpty()) {
            lista = new ArrayList<>();
        }
        model.addAttribute("registros", lista);
        return "historial-servicios";
    }

}
