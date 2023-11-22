package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del cliente", example = "1", required = true)
    private Long id;

    @Schema(description = "Identificación numérica de la entidad", example = "12345678", required = true)
    private int identificacion;

    @Schema(description = "Nombre del cliente", example = "Juan Pérez", required = true)
    private String nombre;

    public Cliente() {
    }

    public Cliente(Long id, int identificacion, String nombre) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
