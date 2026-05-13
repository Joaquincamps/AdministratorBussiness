package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.reserva.ReservaDto;
import com.example.AdministratorBussiness.modelo.Cliente;
import com.example.AdministratorBussiness.modelo.Reserva;
import com.example.AdministratorBussiness.repositorio.ClienteRepositorio;
import com.example.AdministratorBussiness.repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public Reserva crearReserva(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();
        reserva.setFecha(reservaDto.getFecha());
        reserva.setHoraInicio(reservaDto.getHoraInicio());
        reserva.setHoraFin(reservaDto.getHoraFin());
        Cliente cliente = clienteRepositorio.findById(reservaDto.getIdCliente()).orElseThrow(
                () -> new RuntimeException("Cliente no encontrado")
        );
        reserva.setCliente(cliente);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
}
