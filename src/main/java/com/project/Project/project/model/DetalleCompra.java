package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del detalle de compra", example = "1", required = true)
    private Integer id;

    @NotNull(message = "El ID de compra no puede ser nulo.")
    @Min(value = 1, message = "El ID de compra debe ser un entero positivo.")
    @Column(name = "idcompra")
    @Schema(description = "Identificador de la compra asociada", example = "100", required = true)
    private int idcompra;

    @NotNull(message = "El ID de categoría no puede ser nulo.")
    @Min(value = 1, message = "El ID de categoría debe ser un entero positivo.")
    @Column(name = "idcategoria")
    @Schema(description = "Identificador de la categoría del artículo", example = "10", required = true)
    private double idcategoria;

    @NotNull(message = "El ID de artículo no puede ser nulo.")
    @Min(value = 1, message = "El ID de artículo debe ser un entero positivo.")
    @Column(name = "idarticulo")
    @Schema(description = "Identificador del artículo comprado", example = "50", required = true)
    private int idarticulo;

    @NotNull(message = "Las unidades compradas no pueden ser nulas.")
    @Min(value = 1, message = "Las unidades compradas deben ser un entero positivo.")
    @Column(name = "unidadescompradas")
    @Schema(description = "Cantidad de unidades compradas del artículo", example = "5", required = true)
    private int unidadescompradas;

    @NotNull(message = "El valor unitario no puede ser nulo.")
    @PositiveOrZero(message = "El valor unitario debe ser positivo.")
    @Column(name = "valorunidad")
    @Schema(description = "Valor unitario del artículo comprado", example = "200.0", required = true)
    private double valorunidad;

    @NotBlank(message = "El estado no puede estar vacío.")
    @Column(name = "estado")
    @Schema(description = "Estado actual del detalle de compra", example = "1", required = true)
    private Integer estado;

    @NotBlank(message = "El detalle de devolución no puede estar vacío.")
    @Column(name = "detalle_devolucion")
    @Schema(description = "Detalle de la devolución, si aplica", example = "Producto defectuoso", required = false)
    private String detalleDevolucion;


    public DetalleCompra(int idcompra, int idarticulo, int idcategoria, int unidadescompradas, double valorunidad, Integer estado, String detalleDevolucion) {
        this.idcompra = idcompra;
        this.idarticulo = idarticulo;
        this.idcategoria = idcategoria;
        this.unidadescompradas = unidadescompradas;
        this.valorunidad = valorunidad;
        this.estado = estado;
        this.detalleDevolucion = detalleDevolucion;
    }

    public DetalleCompra(int idcompra, int idarticulo, int unidadescompradas, double valorunidad, Integer estado, String detalleDevolucion) {
        this.idcompra = idcompra;
        this.idarticulo = idarticulo;
        this.unidadescompradas = unidadescompradas;
        this.valorunidad = valorunidad;
        this.estado = estado;
        this.detalleDevolucion = detalleDevolucion;
    }

    public DetalleCompra() {
    }

    public String getDetalleDevolucion() {
        return detalleDevolucion;
    }

    public void setDetalleDevolucion(String detalleDevolucion) {
        this.detalleDevolucion = detalleDevolucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getUnidadescompradas() {
        return unidadescompradas;
    }

    public void setUnidadescompradas(int unidadescompradas) {
        this.unidadescompradas = unidadescompradas;
    }

    public double getValorunidad() {
        return valorunidad;
    }

    public double getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(double idcategoria) {
        this.idcategoria = idcategoria;
    }

    public void setValorunidad(double valorunidad) {
        this.valorunidad = valorunidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
