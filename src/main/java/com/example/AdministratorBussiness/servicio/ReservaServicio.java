package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.modelo.Reserva;
import com.example.AdministratorBussiness.repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva){
        return null;
    }
}
