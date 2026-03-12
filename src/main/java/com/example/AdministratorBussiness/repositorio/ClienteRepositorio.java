package com.example.AdministratorBussiness.repositorio;

import com.example.AdministratorBussiness.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    boolean findByEmail(String email);

    Cliente findByTelefono(int telefono);
}
