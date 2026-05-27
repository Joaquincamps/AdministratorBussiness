package com.example.AdministratorBussiness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    @GetMapping("/vista/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
