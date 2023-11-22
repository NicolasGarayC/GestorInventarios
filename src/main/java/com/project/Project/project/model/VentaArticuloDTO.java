package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

public class VentaArticuloDTO {

    @NotEmpty(message = "La lista de artículos de venta no puede estar vacía.")
    @Schema(description = "Lista de artículos incluidos en la venta", example = "[{...}, {...}]")
    private List<ArticuloVentaDTO> articulosVenta;

    @NotNull(message = "El ID del usuario no puede ser nulo.")
    @Schema(description = "Identificador del usuario que realiza la venta", example = "123")
    private int idUsuario;

    @NotNull(message = "El ID del cliente no puede ser nulo.")
    @Schema(description = "Identificador del cliente asociado a la venta", example = "456")
    private int idCliente;

    public VentaArticuloDTO(List<ArticuloVentaDTO> articulosVenta, int idUsuario, int idCliente) {
        this.articulosVenta = articulosVenta;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
    }

    public VentaArticuloDTO() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public List<ArticuloVentaDTO> getArticulosVenta() {
        return articulosVenta;
    }
    public void setArticulosVenta(List<ArticuloVentaDTO> articulosVenta) {
        this.articulosVenta = articulosVenta;
    }

}
