package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.servicio.DtoServicio;
import com.example.AdministratorBussiness.modelo.RegistrarServicio;
import com.example.AdministratorBussiness.modelo.Servicio;
import com.example.AdministratorBussiness.repositorio.RegistrarServicioRepository;
import com.example.AdministratorBussiness.repositorio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiciosDisponibles {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private RegistrarServicioRepository registrarServicioRepository;

    public List<Servicio> listarServicios() {
        if (servicioRepository.findAll().isEmpty()) {
            new RuntimeException("La lista está vacía");
        }
        return servicioRepository.findAll();
    }

    public double calcularPrecioPorServicio(Long id) {
        return servicioRepository.calcularTotalPorServicio(id);
    }

    public void insertarServicio(DtoServicio dtoServicio) {
        Servicio servicio = new Servicio();
        servicio.setTipoServicio(dtoServicio.getNombre());
        servicio.setPrecio(dtoServicio.getPrecio());
        servicioRepository.save(servicio);
    }

    public void actualizarPrecio(Long id, double nuevoPrecio) {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Servicio no encontrado")
        );
        servicio.setPrecio(nuevoPrecio);
        servicioRepository.save(servicio);
    }

    public void registrarServicio(Long id) {
        Servicio servicio = servicioRepository.findById(id).orElseThrow(
                () -> new RuntimeException("El servicio no existe")
        );
        RegistrarServicio registrarServicio = new RegistrarServicio();
        registrarServicio.setServicio(servicio);
        registrarServicio.setPrecio(servicio.getPrecio());
        registrarServicio.setFecha(LocalDate.now());
        registrarServicioRepository.save(registrarServicio);
    }

    public List<RegistrarServicio> listarHistorialServicios() {
        return registrarServicioRepository.findAll();
    }

    public double calcularTotalDeDiaEspecifico(LocalDate fecha) {
        return servicioRepository.calcularTotalPorDiaEspecifico(fecha);
    }

    public List<RegistrarServicio> listarServicioPorDia(LocalDate fecha) {
        return servicioRepository.listarServiciosPorDia(fecha);
    }
}
