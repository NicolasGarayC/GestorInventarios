
package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Identificador único del proveedor", example = "1")
    private Integer id;

    @Column(name = "nombre")
    @Schema(description = "Nombre del proveedor", example = "Proveedor ABC")
    private String nombre;

    @Column(name = "identificacion")
    @Schema(description = "Número de identificación del proveedor", example = "123456789")
    private Integer identificacion;

    @Column(name = "telefono")
    @Schema(description = "Número de teléfono del proveedor", example = "3001234567")
    private Integer telefono;

    @Column(name = "correo")
    @Schema(description = "Correo electrónico del proveedor", example = "contacto@proveedorabc.com")
    private String correo;

    public Proveedor() {
    }

    public Proveedor(String nombre, Integer identificacion, Integer telefono, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}