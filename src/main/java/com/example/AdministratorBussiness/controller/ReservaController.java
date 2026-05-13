package com.example.AdministratorBussiness.controller;

import com.example.AdministratorBussiness.dto.reserva.ReservaDto;
import com.example.AdministratorBussiness.modelo.Cliente;
import com.example.AdministratorBussiness.modelo.Reserva;
import com.example.AdministratorBussiness.servicio.ClienteServicio;
import com.example.AdministratorBussiness.servicio.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaServicio reservaServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @GetMapping("/listar/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteServicio.listarClientes());
    }

    @PostMapping("/crear/reserva")
    public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaDto reservaDto) {
        Reserva reserva = reservaServicio.crearReserva(reservaDto);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/listar/reservas")
    public ResponseEntity<List<Reserva>> listarReservas() {
        return ResponseEntity.ok(reservaServicio.listarReservas());
    }
}
