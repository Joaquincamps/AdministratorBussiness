package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.modelo.Servicio;
import com.example.AdministratorBussiness.repositorio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosDisponibles {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> listarServicios(){
        if(servicioRepository.findAll().isEmpty()){
            new RuntimeException("La lista está vacía");
        }
        return servicioRepository.findAll();
    }
}
