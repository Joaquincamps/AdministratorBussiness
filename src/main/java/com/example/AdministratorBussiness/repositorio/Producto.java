package com.example.AdministratorBussiness.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Producto extends JpaRepository<Producto,Long> {
}
