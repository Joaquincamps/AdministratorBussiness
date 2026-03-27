package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.trabajador.DtoActualizarTrabajador;
import com.example.AdministratorBussiness.dto.trabajador.DtoCrearTrabajador;
import com.example.AdministratorBussiness.modelo.Trabajador;
import com.example.AdministratorBussiness.repositorio.TrabajadorRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            trabajador.setHoraEntrada(createTrabajador.getHoraEntrada());
            trabajador.setHoraSalida(createTrabajador.getHoraSalida());
            trabajador.setActivo(createTrabajador.isActivo());
            trabajador.setDeLibranza(createTrabajador.isDeLibranza());
            trabajador.setDeVacaciones(createTrabajador.isDeVacaciones());
            trabajadorRepositorio.save(trabajador);
        }
    }

    public List<Trabajador> listarTrabajadores() {
        List<Trabajador> lista = trabajadorRepositorio.findAll();
        return lista != null ? lista : new ArrayList<>();
    }

    @Transactional
    public void eliminarTrabajador(Long id) {
        Trabajador trabajadorBuscar = trabajadorRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("No existe el trabajador")
        );
        trabajadorRepositorio.deleteById(id);
    }

    public Trabajador obtenerTrabajador(Long id) {
        Trabajador trabajadorBuscar = trabajadorRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("No existe el trabajador")
        );
        return trabajadorBuscar;
    }

    public void actualizarTrabajador(DtoActualizarTrabajador updateTrabajador ,Long id) {
        Trabajador trabajadorBuscar = trabajadorRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("No existe el trabajador")
        );

        trabajadorBuscar.setPuesto(updateTrabajador.getPuesto());
        trabajadorBuscar.setSalario(updateTrabajador.getSalario());
        trabajadorBuscar.setHoraEntrada(updateTrabajador.getHoraSalida());
        trabajadorBuscar.setHoraSalida(updateTrabajador.getHoraSalida());
        trabajadorBuscar.setActivo(updateTrabajador.isActivo());
        trabajadorBuscar.setDeVacaciones(updateTrabajador.isDeVacaciones());
        trabajadorBuscar.setDeLibranza(updateTrabajador.isDeLibranza());
        trabajadorRepositorio.save(trabajadorBuscar);
    }

    public void importarTrabajadoresCsv(MultipartFile fichero) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fichero.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] array = linea.split(";");
                Trabajador trabajador = new Trabajador();
                trabajador.setNombre(array[0]);
                trabajador.setApellido(array[1]);
                trabajador.setPuesto(array[2]);
                trabajador.setEmail(array[3]);
                trabajador.setSalario(Double.valueOf(array[4]));
                trabajador.setHoraEntrada(array[5]);
                trabajador.setHoraSalida(array[6]);
                trabajador.setActivo(Boolean.valueOf(array[7]));
                trabajador.setDeVacaciones(Boolean.valueOf(array[8]));
                trabajador.setDeLibranza(Boolean.valueOf(array[9]));
                trabajadorRepositorio.save(trabajador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
