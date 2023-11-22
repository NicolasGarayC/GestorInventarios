package com.project.Project.project.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articulo_categoria")
public class ArticuloCategoria {
    @Id
    @Schema(description = "Identificador único de la relación entre artículo y categoría", example = "1", required = true)
    private Integer id;

    @Schema(description = "Identificador del artículo", example = "101", required = true)
    private Integer idarticulo;

    @Schema(description = "Identificador de la categoría", example = "5", required = true)
    private Integer idcategoria;
    // Constructor vacío (necesario para JPA)
    public ArticuloCategoria() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Constructor con parámetros


    public ArticuloCategoria(Integer id, Integer idarticulo, Integer idcategoria) {
        this.id = id;
        this.idarticulo = idarticulo;
        this.idcategoria = idcategoria;
    }

    // Getters y setters
    public Integer getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(Integer idarticulo) {
        this.idarticulo = idarticulo;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }
}

