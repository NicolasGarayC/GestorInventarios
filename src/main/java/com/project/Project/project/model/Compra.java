package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la compra", example = "1", required = true)
    private Integer id;

    @PositiveOrZero(message = "El valor total no puede ser negativo.")
    @Schema(description = "Valor total de la compra", example = "1000.50", required = true)
    private double valortotal;

    @PastOrPresent(message = "La fecha de compra no puede estar en el futuro.")
    @Schema(description = "Fecha en la que se realizó la compra", example = "2023-03-15", required = true)
    private Date fechacompra;


    public Compra() {
        this.fechacompra = new Date();
    }

    public Compra(double valortotal) {
        this.valortotal = valortotal;
        this.fechacompra = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public LocalDate getFecha() {
        return null;
    }

    public LocalDate getFechaCompra() {
        return null;
    }
}
