package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.dto.proveedor.DtoCrearProveedor;
import com.example.AdministratorBussiness.modelo.Proveedor;
import com.example.AdministratorBussiness.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public void registrarProveedor(DtoCrearProveedor createProveedor) {
        if (proveedorRepositorio.existsByEmail(createProveedor.getEmai())) {
            throw new RuntimeException("El cliente ya existe");
        } else {
            Proveedor proveedor = new Proveedor();
            proveedor.setEmail(createProveedor.getEmai());
            proveedor.setNombre(createProveedor.getNombre());
            proveedor.setContacto(createProveedor.getContacto());
            proveedor.setTelefono(createProveedor.getTelefono());
            proveedorRepositorio.save(proveedor);
        }
    }

    public List<Proveedor> consultarProveedores() {
        return proveedorRepositorio.findAll();
    }
}