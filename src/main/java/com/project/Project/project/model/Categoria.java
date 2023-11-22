package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;


@Entity
@Table(name = "categorias")

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Identificador único de la categoría", example = "1", required = true)
    private int id;

    @Column(name = "nombrecategoria", length = 45)
    @Schema(description = "Nombre de la categoría", example = "Electrónica", required = true)
    private String nombreCategorias;
    // Constructor
    public Categoria() {
    }


    public Categoria(String nombreCategorias) {
           this.nombreCategorias = nombreCategorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCategorias() {
        return nombreCategorias;
    }

    public void setNombreCategorias(String nombreCategorias) {
        this.nombreCategorias = nombreCategorias;
    }
}
