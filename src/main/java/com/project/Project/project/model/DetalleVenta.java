package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del detalle de venta", example = "1", required = true)
    private int id;

    @NotNull(message = "El ID de venta no puede ser nulo.")
    @Min(value = 1, message = "El ID de venta debe ser un entero positivo.")
    @Column(name = "idventa", nullable = false)
    @Schema(description = "Identificador de la venta asociada", example = "100", required = true)
    private int idventa;

    @NotNull(message = "El ID de artículo no puede ser nulo.")
    @Min(value = 1, message = "El ID de artículo debe ser un entero positivo.")
    @Column(name = "idarticulo", nullable = false)
    @Schema(description = "Identificador del artículo vendido", example = "50", required = true)
    private int idarticulo;

    @NotNull(message = "Las unidades vendidas no pueden ser nulas.")
    @Min(value = 1, message = "Las unidades vendidas deben ser un entero positivo.")
    @Column(name = "unidadesvendidas", nullable = false)
    @Schema(description = "Cantidad de unidades vendidas del artículo", example = "5", required = true)
    private int unidadesvendidas;

    @NotNull(message = "El valor unitario no puede ser nulo.")
    @PositiveOrZero(message = "El valor unitario debe ser positivo.")
    @Column(name = "valorunidad", nullable = false)
    @Schema(description = "Valor unitario del artículo vendido", example = "200.0", required = true)
    private Double valorunidad;

    @NotNull(message = "El ID de categoría no puede ser nulo.")
    @Min(value = 1, message = "El ID de categoría debe ser un entero positivo.")
    @Column(name = "idcategoria", nullable = false)
    @Schema(description = "Identificador de la categoría del artículo", example = "10", required = true)
    private int idcategoria;

    @NotBlank(message = "El estado no puede estar vacío.")
    @Column(name = "estado", length = 45)
    @Schema(description = "Estado actual del detalle de venta", example = "1", required = true)
    private Integer estado;

    @NotBlank(message = "El detalle de devolución no puede estar vacío.")
    @Column(name = "detalle_devolucion", length = 45)
    @Schema(description = "Detalle de la devolución, si aplica", example = "Producto defectuoso", required = false)
    private String detalleDevolucion;

    public DetalleVenta() {
    }

    public DetalleVenta(int id, int idventa, int idarticulo, int unidadesvendidas, Double valorunidad, int idcategoria, Integer estado, String detalleDevolucion) {
        this.id = id;
        this.idventa = idventa;
        this.idarticulo = idarticulo;
        this.unidadesvendidas = unidadesvendidas;
        this.valorunidad = valorunidad;
        this.idcategoria = idcategoria;
        this.estado = estado;
        this.detalleDevolucion = detalleDevolucion;
    }


    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDetalleDevolucion() {
        return detalleDevolucion;
    }

    public void setDetalleDevolucion(String detalleDevolucion) {
        this.detalleDevolucion = detalleDevolucion;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdventa() {
        return idventa;
    }
    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }
    public int getIdarticulo() {
        return idarticulo;
    }
    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }
    public int getUnidadesvendidas() {
        return unidadesvendidas;
    }
    public void setUnidadesvendidas(int unidadesvendidas) {
        this.unidadesvendidas = unidadesvendidas;
    }
    public Double getValorunidad() {
        return valorunidad;
    }
    public void setValorunidad(Double valorunidad) {
        this.valorunidad = valorunidad;
    }
    public int getIdcategoria() {
        return idcategoria;
    }
    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }
}