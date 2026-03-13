package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.cliente.DtoCrearCliente;
import com.example.AdministratorBussiness.modelo.Cliente;
import com.example.AdministratorBussiness.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public void crearCliente(DtoCrearCliente createCliente) {
        if (clienteRepositorio.findByEmail(createCliente.getEmail())) {
            throw new RuntimeException(("El cliente ya existe"));
        } else {
            Cliente cliente = new Cliente();
            cliente.setNombre(createCliente.getNombre());
            cliente.setEmail(createCliente.getEmail());
            cliente.setTelefono(createCliente.getTelefono());
            clienteRepositorio.save(cliente);
        }
    }

    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }

    public void buscarClientePorId(Long id) {
        Cliente clienteBuscar = clienteRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente no encontrado")
        );
    }

    public void buscarClientePorTelefono(int telefono){
        Cliente clienteBuscar = clienteRepositorio.findByTelefono(telefono);
    }
}
