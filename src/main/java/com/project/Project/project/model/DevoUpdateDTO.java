package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.ArrayList;

public class DevoUpdateDTO {

    @NotNull(message = "El ID de compra no puede ser nulo.")
    @Min(value = 1, message = "El ID de compra debe ser un entero positivo.")
    @Schema(description = "Identificador único de la compra", example = "123", required = true)
    private Integer idCompra;

    @NotBlank(message = "La descripción no puede estar vacía.")
    @Schema(description = "Descripción detallada de la compra o del proceso", example = "Compra de artículos electrónicos", required = true)
    private String descripcion;

    @NotNull(message = "La lista de artículos devueltos no puede ser nula.")
    @Schema(description = "Lista de artículos que han sido devueltos", required = true)
    private ArrayList devuelto;  // Considera especificar el tipo de elementos en el ArrayList, por ejemplo, ArrayList<Articulo>

    public DevoUpdateDTO() {
    }

    public DevoUpdateDTO(Integer idCompra, String descripcion, ArrayList devuelto) {
        this.idCompra = idCompra;
        this.descripcion = descripcion;
        this.devuelto = devuelto;
    }
    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(ArrayList devuelto) {
        this.devuelto = devuelto;
    }
}
