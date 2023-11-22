package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ArticulosCompraDTO {

    @NotNull(message = "El artículo no puede ser nulo.")
    @Schema(description = "Detalles del artículo", required = true)
    private Articulo articulo;

    @Min(value = 0, message = "Las unidades compradas no pueden ser negativas.")
    @Schema(description = "Número de unidades del artículo compradas", example = "10", required = true)
    private int unidadesCompradas;

    @PositiveOrZero(message = "El valor por unidad no puede ser negativo.")
    @Schema(description = "Valor por unidad del artículo", example = "50.0", required = true)
    private double valorUnidad;

    @PositiveOrZero(message = "El valor total no puede ser negativo.")
    @Schema(description = "Valor total de la compra del artículo", example = "500.0", required = true)
    private double valorTotal;

    @NotNull(message = "El ID de la categoría no puede ser nulo.")
    @Schema(description = "Identificador de la categoría del artículo", example = "3", required = true)
    private Integer idCategoria;

    @NotNull(message = "El estado no puede ser nulo.")
    @Schema(description = "Estado del artículo en la compra", example = "1", required = true)
    private Integer estado;

    public ArticulosCompraDTO(Articulo articulo, int unidadesCompradas, double valorUnidad, Integer idCategoria,Integer estado) {
        this.articulo = articulo;
        this.estado = estado;
        this.unidadesCompradas = unidadesCompradas;
        this.valorUnidad = valorUnidad;
        this.idCategoria = idCategoria;
        this.valorTotal = (unidadesCompradas*valorUnidad);
    }

    public ArticulosCompraDTO() {
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getUnidadesCompradas() {
        return unidadesCompradas;
    }

    public void setUnidadesCompradas(int unidadesCompradas) {
        this.unidadesCompradas = unidadesCompradas;
    }

    public double getValorUnidad() {
        return valorUnidad;
    }

    public void setValorUnidad(double valorUnidad) {
        this.valorUnidad = valorUnidad;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getEstado() {return estado;}

    public void setEstado(Integer estado) {this.estado = estado;}
}
