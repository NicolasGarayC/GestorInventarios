package com.project.Project.project.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class EstadosDTO {

    @NotNull(message = "El ID de operacion no puede ser nulo.")
    @Min(value = 1, message = "El ID de operacion debe ser un entero positivo.")
    @Schema(description = "Identificador único de la operación", example = "101", required = true)
    private Integer operacion;

    @NotNull(message = "La lista de artículos devueltos no puede ser nula.")
    @Schema(description = "Lista de artículos junto con su estado actual", required = true)
    private List<articulosEstadoDTO> articulos;  // Asegúrate de que 'articulosEstadoDTO' esté definido correctamente

    public EstadosDTO() {
    }

    public EstadosDTO(Integer operacion, List<articulosEstadoDTO> articulos) {
        this.operacion = operacion;
        this.articulos = articulos;
    }

    public Integer getOperacion() {
        return operacion;
    }

    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
    }

    public List<articulosEstadoDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<articulosEstadoDTO> articulos) {
        this.articulos = articulos;
    }
}
