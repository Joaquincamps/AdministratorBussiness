package com.example.AdministratorBussiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController{

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/clientes")
    public String agregarCliente(@ModelAttribute DtoCrearCliente DtoCrearCliente, Model model){
        try{
            clienteServicio.agregarCliente(cliente);
            model.addAttribute("mensaje","Cliente agregado con éxito");
            model.addAttribute("cliente",new Cliente());
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cliente",cliente);
        }

        return "clientes";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model){
        model.addAttribute("clientes",clienteServicio.listarClientes());
        model.addAttribute("clientes",new Cliente());
        return "clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(Model model, @PathVariable Long id){
        model.addAttribute("clientes",clienteServicio.eliminarCliente)
    }

    @PostMapping("/clientes/actualizar")
    public String actualizarCliente(DtoActualizarCLiente updateCliente){
        clienteServicio.actualizarCliente(updateCliente);
        return "clientes";
    }   

}