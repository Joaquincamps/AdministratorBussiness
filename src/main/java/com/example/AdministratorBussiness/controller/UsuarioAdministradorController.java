package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.modelo.UsuarioAdministrador;
import com.example.AdministratorBussiness.servicio.UsuarioAdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioAdministradorController {

    @Autowired
    private UsuarioAdministradorServicio usuarioAdministradorServicio;

    @PostMapping("/admin/registrar")
    public String registrarUsuarioAdmin(@ModelAttribute("administrador") UsuarioAdministrador usuarioAdministrador,
                                        RedirectAttributes redirectAttributes) {
        if (usuarioAdministradorServicio.registrarUsuario(usuarioAdministrador)) {
            redirectAttributes.addAttribute("mensajeExito", "El adminstrador fue creado con éxito");
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addAttribute("mensaje", "El usuario ya existe");
            return "redirect:/index";
        }
    }
}
