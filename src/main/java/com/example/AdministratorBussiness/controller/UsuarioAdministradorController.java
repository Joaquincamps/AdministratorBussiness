package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.modelo.UsuarioAdministrador;
import com.example.AdministratorBussiness.servicio.UsuarioAdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioAdministradorController {

    @Autowired
    private UsuarioAdministradorServicio usuarioAdministradorServicio;

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("administrador", new UsuarioAdministrador());
        return "registrar";
    }
    @PostMapping("/admin/registrar")
    public String registrarUsuarioAdmin(@ModelAttribute("administrador") UsuarioAdministrador usuarioAdministrador,
                                        RedirectAttributes redirectAttributes) {
        if (usuarioAdministradorServicio.registrarUsuario(usuarioAdministrador)) {
            redirectAttributes.addAttribute("mensajeExito", "El adminstrador fue creado con éxito");
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addAttribute("mensajeError", "El usuario ya existe");
            return "redirect:/index";
        }
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("loginUsuario", new UsuarioAdministrador());
        return "index";
    }
    @PostMapping("/admin/login")
    public String logearAdmin(@ModelAttribute("loginUsuario") UsuarioAdministrador usuarioAdministrador,
                              RedirectAttributes redirectAttributes) {
        if (usuarioAdministradorServicio.logearAdmin(usuarioAdministrador)) {
            redirectAttributes.addAttribute("mensajeExito", "El administrador ya existe");
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addAttribute("mensajeError",
                    "El usuario no existe, tiene que iniciar sesión");
            return "redirect:/index";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
