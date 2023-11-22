package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class articulosEstadoDTO{
    @Schema(description = "Identificador único del artículo", example = "123", required = true)
    private Integer id;

    @Schema(description = "Estado actual del artículo", example = "1", required = true)
    private Integer estado;

    public articulosEstadoDTO(Integer id, Integer estado) {
        this.id = id;
        this.estado = estado;
    }

    public articulosEstadoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
