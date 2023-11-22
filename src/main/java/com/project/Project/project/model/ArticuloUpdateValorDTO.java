package com.project.Project.project.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
public class ArticuloUpdateValorDTO {

    @Schema(description = "Identificador único del elemento", example = "101", required = true)
    private Integer id;

    @Schema(description = "Valor unitario del elemento, no puede ser negativo", example = "50.0", required = true)
    @PositiveOrZero(message = "El valor unitario no puede ser negativo.")

    private double valorunitario;
    public ArticuloUpdateValorDTO() {
    }

    public ArticuloUpdateValorDTO(Integer id, double valorunitario) {
        this.id = id;
        this.valorunitario = valorunitario;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }
}
