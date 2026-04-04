package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.proveedor.DtoActuProveedor;
import com.example.AdministratorBussiness.dto.proveedor.DtoCrearProveedor;
import com.example.AdministratorBussiness.modelo.Proveedor;
import com.example.AdministratorBussiness.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public void registrarProveedor(DtoCrearProveedor createProveedor) {
        if (proveedorRepositorio.existsByEmail(createProveedor.getEmail())) {
            throw new RuntimeException("El cliente ya existe");
        } else {
            Proveedor proveedor = new Proveedor();
            proveedor.setEmail(createProveedor.getEmail());
            proveedor.setNombreEmpresa(createProveedor.getNombre());
            proveedor.setPersonaContacto(createProveedor.getContacto());
            proveedor.setTelefono(createProveedor.getTelefono());
            proveedorRepositorio.save(proveedor);
        }
    }

    public List<Proveedor> consultarProveedores() {
        return proveedorRepositorio.findAll();
    }

    public void subirProveedorPorFichero(MultipartFile fichero) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fichero.getInputStream()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] array = linea.split(";");
                Proveedor proveedor = new Proveedor();
                proveedor.setNombreEmpresa(array[0]);
                proveedor.setPersonaContacto(array[1]);
                proveedor.setTelefono(Integer.parseInt(array[2]));
                proveedor.setActivo(Boolean.valueOf(array[3]));
                proveedor.setEmail(array[4]);
                proveedorRepositorio.save(proveedor);
            }
        } catch (Exception e) {

        }
    }

    public void eliminarProveedor(Long id) {
        Proveedor proveedor = proveedorRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("El proveedor no existe")
        );
        proveedorRepositorio.deleteById(id);
    }

    public List<Proveedor> buscarProveedorPorParametro(String entrada) {
        return proveedorRepositorio.findProveedor(entrada);
    }

    public Proveedor buscarPorId(Long id) {
        Proveedor pro = proveedorRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("Proveedor no encontrado")
        );
        return pro;
    }

    public void actualizarProveedor(DtoActuProveedor dtoActuProveedor, Long id) {
        Proveedor proveedorBuscar = proveedorRepositorio.findById(id).orElseThrow(
                () -> new RuntimeException("Proveedor no encontrado")
        );
        proveedorBuscar.setNombreEmpresa(dtoActuProveedor.getNombreEmpresa());
        proveedorBuscar.setPersonaContacto(dtoActuProveedor.getPersonaContacto());
        proveedorBuscar.setTelefono(dtoActuProveedor.getTelefono());
        proveedorBuscar.setEmail(dtoActuProveedor.getEmail());
        proveedorRepositorio.save(proveedorBuscar);
    }
}