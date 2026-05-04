package com.example.AdministratorBussiness.modelo;

import com.example.AdministratorBussiness.Enums.opServicio.EnumServicio;
import com.example.AdministratorBussiness.Enums.opServicio.TiempoServicio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoServicio;

    private double precio;

    public Servicio(String tipoServicio, double precio) {
        this.tipoServicio = tipoServicio;
        this.precio = precio;
    }
}
