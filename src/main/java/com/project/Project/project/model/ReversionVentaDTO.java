package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

public class ReversionVentaDTO {

    @NotNull(message = "El ID de venta no puede ser nulo.")
    @Min(value = 1, message = "El ID de venta debe ser un número positivo.")
    @Schema(description = "Identificador único de la venta a revertir", example = "100")
    private int idVenta;

    @NotBlank(message = "El motivo de reversión no puede estar vacío.")
    @Schema(description = "Motivo por el cual se realiza la reversión de la venta", example = "Producto defectuoso")
    private String motivoReversion;

    @NotNull(message = "La lista de artículos devueltos no puede ser nula.")
    @Schema(description = "Lista de productos revertidos en la venta")
    private List<ProductoRevertidoDTO> devuelto;

    @NotNull(message = "La confirmación del usuario es requerida.")
    @Schema(description = "Indica si el usuario ha confirmado la reversión", example = "true")
    private boolean confirmacionUsuario;

    public ReversionVentaDTO() {
    }

    public ReversionVentaDTO(int idVenta, String motivoReversion, List<ProductoRevertidoDTO> devuelto, boolean confirmacionUsuario) {
        this.idVenta = idVenta;
        this.motivoReversion = motivoReversion;
        this.devuelto = devuelto;
        this.confirmacionUsuario = confirmacionUsuario;
    }


    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getMotivoReversion() {
        return motivoReversion;
    }

    public void setMotivoReversion(String motivoReversion) {
        this.motivoReversion = motivoReversion;
    }

    public ArrayList getDevuelto() {
        return (ArrayList) devuelto;
    }

    public void setDevuelto(List<ProductoRevertidoDTO> devuelto) {
        this.devuelto = devuelto;
    }

    public boolean isConfirmacionUsuario() {
        return confirmacionUsuario;
    }

    public void setConfirmacionUsuario(boolean confirmacionUsuario) {
        this.confirmacionUsuario = confirmacionUsuario;
    }
}

