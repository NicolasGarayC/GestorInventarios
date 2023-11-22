package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class ArticuloVentaDTO {

    @Schema(description = "Identificador del artículo", example = "123", required = true)
    @Min(value = 1, message = "El ID del artículo debe ser un entero positivo.")
    private int articulo;

    @Schema(description = "Número de unidades vendidas del artículo", example = "10", required = true)
    @Min(value = 1, message = "Las unidades vendidas deben ser al menos 1.")
    private int unidadesVendidas;

    @Schema(description = "Estado del artículo", example = "1", required = true)
    @Min(value = 1, message = "El estado debe ser un entero positivo.")
    private int estado;
    public ArticuloVentaDTO() {
    }

    public ArticuloVentaDTO(int articulo, int unidadesVendidas, int estado) {
        this.articulo = articulo;
        this.unidadesVendidas = unidadesVendidas;
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getArticulo() {
        return articulo;
    }

    public void setArticulo(int articulo) {
        this.articulo = articulo;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }
}