package com.project.Project.project.model;

import jakarta.validation.constraints.*;

public class ArticuloVentaDTO {

    @Min(value = 1, message = "El ID del artículo debe ser un entero positivo.")
    private int articulo;

    @Min(value = 1, message = "Las unidades vendidas deben ser al menos 1.")
    private int unidadesVendidas;

    public ArticuloVentaDTO() {
    }

    public ArticuloVentaDTO(int articulo, int unidadesVendidas) {
        this.articulo = articulo;
        this.unidadesVendidas = unidadesVendidas;
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
