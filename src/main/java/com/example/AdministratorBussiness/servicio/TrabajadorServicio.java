package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.trabajador.DtoActualizarTrabajador;
import com.example.AdministratorBussiness.dto.trabajador.DtoCrearTrabajador;
import com.example.AdministratorBussiness.modelo.Trabajador;
import com.example.AdministratorBussiness.repositorio.TrabajadorRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrabajadorServicio {

    @Autowired
    private TrabajadorRepositorio trabajadorRepositorio;

    public void agregarTrabajador(DtoCrearTrabajador createTrabajador) {
        if (trabajadorRepositorio.existsByEmail(createTrabajador.getEmail())) {
            throw new RuntimeException("El trabajador ya existe.");
        } else {
            Trabajador trabajador = new Trabajador();
            trabajador.setNombre(createTrabajador.getNombre());
            trabajador.setApellido(createTrabajador.getApellido());
            trabajador.setEmail(createTrabajador.getEmail());
            trabajador.setPuesto(createTrabajador.getPuesto());
            trabajador.setSalario(createTrabajador.getSalario());
            trabajador.setFechaEntrada(createTrabajador.getFechaEntrada());
            trabajador.setActivo(createTrabajador.isActivo());
            trabajador.setDeLibranza(createTrabajador.isDeLibranza());
            trabajador.setDeVacaciones(createTrabajador.isDeVacaciones());
            trabajadorRepositorio.save(trabajador);
        }
    }

    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepositorio.findAll();
    }

    @Transactional
    public void eliminarTrabajador(Long id) {
        Trabajador trabajadorBuscar = trabajadorRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("No existe el trabajador")
        );
        trabajadorRepositorio.deleteById(id);
    }

    public void actualizarTrabajador(DtoActualizarTrabajador updateTrabajador) {
        Trabajador trabajadorBuscar = trabajadorRepositorio.findById(updateTrabajador.getId()).orElseThrow(
                () -> new RuntimeException("No existe el trabajador")
        );

        trabajadorBuscar.setPuesto(updateTrabajador.getPuesto());
        trabajadorBuscar.setSalario(updateTrabajador.getSalario());
        trabajadorBuscar.setFechaSalida(updateTrabajador.getFechaSalida());
        trabajadorBuscar.setActivo(updateTrabajador.isActivo());
        trabajadorBuscar.setDeVacaciones(updateTrabajador.isDeVacaciones());
        trabajadorBuscar.setDeLibranza(updateTrabajador.isDeLibranza());
        trabajadorRepositorio.save(trabajadorBuscar);
    }
}
