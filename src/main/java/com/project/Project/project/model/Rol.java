package com.project.Project.project.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "roles")

public class Rol {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del rol", example = "1")
    private Integer id;

    @Column(name = "rol")
    @Schema(description = "Nombre del rol", example = "ADMIN")
    private String rol;

    public Rol() {}

    public Rol(String rol) {
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", rol='" + rol + '\'' +
                '}';
    }
}