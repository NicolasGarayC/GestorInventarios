package com.project.Project.project.model;

import jakarta.persistence.*;


@Entity
@Table(name = "categorias")

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombrecategoria", length = 45)
    private String nombreCategorias;

    // Constructor
    public Categoria() {
    }


    public Categoria(String nombreCategorias) {
           this.nombreCategorias = nombreCategorias;
    }

<<<<<<< HEAD

=======
>>>>>>> 8ddc05484d0c084d5aa56624e92c2e65174af8a1
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
