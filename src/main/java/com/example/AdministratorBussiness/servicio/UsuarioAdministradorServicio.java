package com.example.AdministratorBussiness.servicio;

import com.example.AdministratorBussiness.modelo.UsuarioAdministrador;
import com.example.AdministratorBussiness.repositorio.UsuarioAdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAdministradorServicio {

    @Autowired
    private UsuarioAdministradorRepositorio usuarioAdministradorRepositorio;

    public boolean registrarUsuario(UsuarioAdministrador userAdminstrador) {
        if (usuarioAdministradorRepositorio.existsByNombre(userAdminstrador.getNombre())) {
            return false;
        } else {
            usuarioAdministradorRepositorio.save(userAdminstrador);
            return true;
        }
    }

    public boolean logearAdmin(UsuarioAdministrador usuarioAdministrador) {
        if (usuarioAdministradorRepositorio.existsByNombre(usuarioAdministrador.getNombre()) &&
                usuarioAdministradorRepositorio.existsByPassword(usuarioAdministrador.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
