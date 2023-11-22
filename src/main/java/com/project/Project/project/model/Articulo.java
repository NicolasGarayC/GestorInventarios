package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del artículo", example = "1", required = true)
    private int id;

    @NotBlank(message = "El nombre del artículo no puede estar vacío.")
    @Schema(description = "Nombre del artículo", example = "Laptop", required = true)
    private String nombrearticulo;

    @NotBlank(message = "La marca no puede estar vacía.")
    @Schema(description = "Marca del artículo", example = "Dell", required = true)
    private String marca;

    @NotBlank(message = "El modelo no puede estar vacío.")
    @Schema(description = "Modelo del artículo", example = "Inspiron 15", required = true)
    private String modelo;

    @NotBlank(message = "El color no puede estar vacío.")
    @Schema(description = "Color del artículo", example = "Negro", required = true)
    private String color;

    @NotBlank(message = "La unidad de medida no puede estar vacía.")
    @Schema(description = "Unidad de medida del artículo", example = "Unidades", required = true)
    private String unidaddemedida;

    @Min(value = 0, message = "Las unidades disponibles no pueden ser negativas.")
    @Schema(description = "Unidades disponibles del artículo", example = "50", required = true)
    private int unidadesdisponibles;

    @PositiveOrZero(message = "El valor unitario no puede ser negativo.")
    @Schema(description = "Valor unitario del artículo", example = "500.00", required = true)
    private double valorunitario;

    public Articulo() {
    }

    public Articulo(int id, String nombrearticulo, String marca, String modelo, String color, String unidaddemedida, int unidadesdisponibles, double valorunitario) {
        this.id = id;
        this.nombrearticulo = nombrearticulo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.unidaddemedida = unidaddemedida;
        this.unidadesdisponibles = unidadesdisponibles;
        this.valorunitario = valorunitario;
    }

    public Articulo(String nombrearticulo, String marca, String modelo, String color, String unidaddemedida) {
        this.nombrearticulo = nombrearticulo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.unidaddemedida = unidaddemedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrearticulo() {
        return nombrearticulo;
    }

    public void setNombrearticulo(String nombrearticulo) {
        this.nombrearticulo = nombrearticulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUnidaddemedida() {
        return unidaddemedida;
    }

    public void setUnidaddemedida(String unidaddemedida) {
        this.unidaddemedida = unidaddemedida;
    }

    public int getUnidadesdisponibles() {
        return unidadesdisponibles;
    }

    public void setUnidadesdisponibles(int unidadesdisponibles) {
        this.unidadesdisponibles = unidadesdisponibles;
    }

    public double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }
}