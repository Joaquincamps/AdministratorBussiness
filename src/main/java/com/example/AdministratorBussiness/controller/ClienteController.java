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
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    @PostMapping("/clientes")
    public String agregarCliente(@ModelAttribute DtoCrearCliente DtoCrearCliente, Model model) {
        try {
            clienteServicio.crearCliente(DtoCrearCliente);
            model.addAttribute("mensaje", "Cliente agregado con éxito");
            model.addAttribute("cliente", new Cliente());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cliente", DtoCrearCliente);
        }

        return "clientes";
    }

    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> lista = clienteServicio.listarClientes();
        if (lista == null) {
            lista = new ArrayList<>();
        }
        model.addAttribute("clientes", lista);
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarClientes(Model model) {
        model.addAttribute("cliente", new DtoCrearCliente());
        return "crear-cliente";
    }

    @PostMapping("/clientes/nuevo")
    public String crearCliente(@ModelAttribute("cliente") DtoCrearCliente crearCliente,
                               RedirectAttributes redirectAttributes) {
        try {
            clienteServicio.crearCliente(crearCliente);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente insertado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Error", "No se puedo insetar el cliente");
        }
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/importar")
    public String importarClientesCsv(@RequestParam("file") MultipartFile fichero,
                                      RedirectAttributes redirectAttributes) {
        if (fichero.isEmpty()) {
            redirectAttributes.addAttribute("mensaje", "EL fichero está vacío");
        }
        clienteServicio.importarClienteFichero(fichero);
        redirectAttributes.addAttribute("mensjae", "Fichero agregado con éxito");
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(Model model, @PathVariable Long id) {
        try {
            clienteServicio.eliminarClientePorId(id);
            model.addAttribute("mensaje", "Trabajador eliminado con éxito");
        } catch (Exception e) {
            model.addAttribute("Error", "Trabajador no eliminado");
        }
        model.addAttribute("clientes", clienteServicio.listarClientes());
        model.addAttribute("cliente", new Cliente());
        return "clientes";
    }

    @PostMapping("/clientes/actualizar")
    public String actualizarCliente(DtoActualizarCliente updateCliente) {
        clienteServicio.actuCliente(updateCliente);
        return "clientes";
    }

    @GetMapping("/clientes/enviar/mensaje/{id}")
    public String verFormularioEnviarMensaje(Model model, @PathVariable Long id) {
        Cliente clienteBuscar = clienteServicio.buscarClientePorId(id);
        model.addAttribute("cliente", clienteBuscar);
        return "enviar-mensaje";
    }

    @PostMapping("/clientes/enviar/mensaje")
    public String enviarMensajeAlCliente(@RequestParam String mensaje,
                                         @RequestParam String telefono,
                                         RedirectAttributes redirectAttributes) {
        clienteServicio.enviarMensajeAlCliente(telefono, mensaje);
        redirectAttributes.addAttribute("mensaje", "Whatsapp enviado con éxito");
        return "redirect:/clientes";
    }

    @PostMapping("/clientes/llamar/{id}")
    public String realizarLlamada(RedirectAttributes redirectAttributes,
                                  @PathVariable Long id) {
        Cliente clienteBuscar = clienteServicio.buscarClientePorId(id);
        int numeroTelefono = clienteBuscar.getTelefono();
        String numeroString = String.valueOf(numeroTelefono);
        clienteServicio.realizarLlamadaACliente(numeroString);
        redirectAttributes.addAttribute("mensaje", "llamada realizada con éxito");
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/llamar/{id}")
    public String obtenerVistaLlamar() {
        return "clientes";
    }

}