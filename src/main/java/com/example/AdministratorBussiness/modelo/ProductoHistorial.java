package com.example.AdministratorBussiness.modelo;

import com.example.AdministratorBussiness.Enums.opEnum.Operacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProductoHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidad;

    @Enumerated(EnumType.STRING)
    private Operacion operacion;

    private int stockFinal;

    private LocalDateTime fecha;

    public ProductoHistorial() {
    }

    public ProductoHistorial(Producto producto, int cantidad, Operacion operacion, int stockFinal, LocalDateTime fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.operacion = operacion;
        this.stockFinal = stockFinal;
        this.fecha = fecha;
    }


}
