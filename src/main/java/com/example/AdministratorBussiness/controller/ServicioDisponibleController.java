package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.servicio.DtoServicio;
import com.example.AdministratorBussiness.modelo.Servicio;
import com.example.AdministratorBussiness.servicio.ServiciosDisponibles;
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
                                @ModelAttribute("servicio") DtoServicio dtoServicio){

    }
}
