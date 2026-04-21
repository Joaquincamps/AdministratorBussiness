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

        @Enumerated(EnumType.STRING)
        private EnumServicio enumServicio;

    private double precio;

    public Servicio(EnumServicio enumServicio, double precio) {
        this.enumServicio = enumServicio;
        this.precio = precio;
    }
}
