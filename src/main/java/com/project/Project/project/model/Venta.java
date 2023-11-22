package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la venta", example = "1")
    private int id;

    @Column(name = "valortotal")
    @NotNull(message = "El valor total no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor total debe ser mayor que 0.")
    @Schema(description = "Valor total de la venta", example = "100.0")
    private double valorTotal;

    @Column(name = "fechaventa")
    @NotNull(message = "La fecha de venta no puede ser nula.")
    @PastOrPresent(message = "La fecha de venta no puede ser futura.")
    @Schema(description = "Fecha en que se realizó la venta", example = "2023-01-01")
    private Date fechaVenta;

    public Venta() {
        this.fechaVenta = new Date(); // Establecer la fecha actual por defecto
    }

    public Venta(int id, double valorTotal, Date fechaVenta) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.fechaVenta = fechaVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }


}
