package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.cliente.DtoActualizarCliente;
import com.example.AdministratorBussiness.dto.cliente.DtoCrearCliente;
import com.example.AdministratorBussiness.modelo.Cliente;
import com.example.AdministratorBussiness.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    public void buscarClientePorTelefono(int telefono) {
        Cliente clienteBuscar = clienteRepositorio.findByTelefono(telefono);
    }

    public void eliminarClientePorId(Long id) {
        Cliente clienteBuscar = clienteRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("El cliente no exsite")
        );
        clienteRepositorio.deleteById(id);
    }

    public void actuCliente(DtoActualizarCliente dtoActualizarCliente) {
        Cliente clienteBuscar = clienteRepositorio.findById(dtoActualizarCliente.getId()).orElseThrow(
                () -> new RuntimeException("El cliente no éxiste.")
        );
        clienteBuscar.setTelefono(dtoActualizarCliente.getTelefono());
        clienteBuscar.setEmail(dtoActualizarCliente.getEmail());
        clienteRepositorio.save(clienteBuscar);
    }

    public void importarClienteFichero(MultipartFile fichero){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fichero.getInputStream()))){
            String linea;
            while (( linea = br.readLine()) != null){
                String [] array = linea.split(";");
                Cliente cliente = new Cliente();
                cliente.setNombre(array[0]);
                cliente.setTelefono(Integer.parseInt(array[1]));
                cliente.setEmail(array[2]);
                clienteRepositorio.save(cliente);
            }
        }catch (Exception e){

        }
    }

}
