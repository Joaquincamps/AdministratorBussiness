package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.cliente.DtoActualizarCliente;
import com.example.AdministratorBussiness.dto.cliente.DtoCrearCliente;
import com.example.AdministratorBussiness.modelo.Cliente;
import com.example.AdministratorBussiness.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClienteController{

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/clientes")
    public String agregarCliente(@ModelAttribute DtoCrearCliente DtoCrearCliente, Model model){
        try{
            clienteServicio.crearCliente(DtoCrearCliente);
            model.addAttribute("mensaje","Cliente agregado con éxito");
            model.addAttribute("cliente",new Cliente());
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cliente",DtoCrearCliente);
        }

        return "clientes";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model){
        List<Cliente> lista = clienteServicio.listarClientes();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        model.addAttribute("clientes",lista);
        return "clientes";
    }

    @PostMapping("/clientes/importar")
    public String importarClientesCsv(@RequestParam("file")MultipartFile fichero,
                                      RedirectAttributes redirectAttributes){
        if(fichero.isEmpty()){
            redirectAttributes.addAttribute("mensaje","EL fichero está vacío");
        }
        clienteServicio.importarClienteFichero(fichero);
        redirectAttributes.addAttribute("mensjae","Fichero agregado con éxito");
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(Model model, @PathVariable Long id){
        clienteServicio.eliminarClientePorId(id);
        model.addAttribute("clientes",model);
        return "clientes";
    }

    @PostMapping("/clientes/actualizar")
    public String actualizarCliente(DtoActualizarCliente updateCliente){
        clienteServicio.actuCliente(updateCliente);
        return "clientes";
    }   

}