package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.configuration.SipApiProperties;
import com.example.AdministratorBussiness.dto.cliente.DtoActualizarCliente;
import com.example.AdministratorBussiness.dto.cliente.DtoCrearCliente;
import com.example.AdministratorBussiness.modelo.Cliente;
import com.example.AdministratorBussiness.repositorio.ClienteRepositorio;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

@Service
public class ClienteServicio {

    private SipApiProperties properties;

    public ClienteServicio(SipApiProperties properties) {
        this.properties = properties;
    }

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public void crearCliente(DtoCrearCliente createCliente) {
        if (clienteRepositorio.existsByEmail(createCliente.getEmail())) {
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

    public Cliente buscarClientePorId(Long id) {
        Cliente clienteBuscar = clienteRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("Cliente no encontrado")
        );
        return clienteBuscar;
    }

    public void buscarClientePorTelefono(int telefono) {
        Cliente clienteBuscar = clienteRepositorio.existsByTelefono(telefono);
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

    public void importarClienteFichero(MultipartFile fichero) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fichero.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] array = linea.split(";");
                Cliente cliente = new Cliente();
                cliente.setNombre(array[0]);
                cliente.setTelefono(Integer.parseInt(array[1]));
                cliente.setEmail(array[2]);
                clienteRepositorio.save(cliente);
            }
        } catch (Exception e) {

        }
    }

    public void enviarMensajeAlCliente(String numeroCliente, String mensajeEnviar) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+34" + numeroCliente),
                new PhoneNumber("whatsapp:+14155238886"),
                mensajeEnviar
        ).create();
        System.out.println(message.getBody());
    }

    public String realizarLlamadaACliente(String numDestino) {
        HttpResponse<String> response = Unirest.post(
                        properties.getBaseUrl() + "/v1/llamar")
                .header("key", properties.getToken())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("iduser", properties.getUser())
                .field("dst", numDestino)
                .asString();

        return response.getBody();
    }
}
