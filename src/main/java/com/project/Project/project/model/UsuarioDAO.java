package com.project.Project.project.model;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class UsuarioDAO {
    @Schema(description = "Identificador único del usuario", example = "1")
    private Integer id;

    @Schema(description = "Correo electrónico del usuario", example = "usuario@example.com")
    private String correo;

    @Schema(description = "Cédula de identidad del usuario", example = "123456789")
    private Integer cedula;

    @Schema(description = "Estado actual del usuario", example = "Activo")
    private String estado;

    @Schema(description = "Indica si el usuario debe cambiar su contraseña", example = "true")
    private boolean cambiarClave;

    @Schema(description = "Fecha del último cambio de contraseña", example = "2023-01-01")
    private Date fechaUltimoCambioClave;

    public UsuarioDAO(Integer id, String correo, Integer cedula, String estado, boolean cambiarClave, Date fechaUltimoCambioClave) {
        this.id = id;
        this.correo = correo;
        this.cedula = cedula;
        this.estado = estado;
        this.cambiarClave = cambiarClave;
        this.fechaUltimoCambioClave = fechaUltimoCambioClave;
    }

    public UsuarioDAO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isCambiarClave() {
        return cambiarClave;
    }

    public void setCambiarClave(boolean cambiarClave) {
        this.cambiarClave = cambiarClave;
    }

    public Date getFechaUltimoCambioClave() {
        return fechaUltimoCambioClave;
    }

    public void setFechaUltimoCambioClave(Date fechaUltimoCambioClave) {
        this.fechaUltimoCambioClave = fechaUltimoCambioClave;
    }

}