package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProductoRevertidoDTO {
    @Schema(description = "Identificador único del artículo", example = "123")
    private int idArticulo;

    @Schema(description = "Cantidad de artículos revertidos", example = "5")
    private int cantidad;

    public ProductoRevertidoDTO() {
    }

    public ProductoRevertidoDTO(int idArticulo, int cantidad) {
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
