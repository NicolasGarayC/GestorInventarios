package com.project.Project.project.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Identificador único del usuario", example = "1")
    private Integer id;

    @Column(name = "correo", length = 45, nullable = false)
    @Schema(description = "Correo electrónico del usuario", example = "usuario@example.com")
    private String correo;

    @Column(name = "passwd", length = 45, nullable = false)
    @Schema(description = "Contraseña del usuario", example = "contraseña123")
    private String passwd;

    @Column(name = "cedula", length = 11)
    @Schema(description = "Cédula de identidad del usuario", example = "123456789")
    private int cedula;

    @Column(name = "nombre", length = 45)
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String nombre;

    @Column(name = "estado", length = 45)
    @Schema(description = "Estado actual del usuario", example = "Activo")
    private String estado;

    @Column(name = "cambiarclave")
    @Schema(description = "Indica si el usuario debe cambiar su contraseña", example = "true")
    private boolean cambiarClave;

    @Column(name = "fecha_ult_cambio_clave")
    @Schema(description = "Fecha del último cambio de contraseña", example = "2023-01-01")
    private Date fechaUltimoCambioClave;

    @Column(name = "token")
    @Schema(description = "Token de autenticación o verificación", example = "123456")
    private Integer token;

    @Column(name = "intentos_fallidos")
    @Schema(description = "Número de intentos fallidos de inicio de sesión", example = "0")
    private Integer intentosFallidos;

    @Column(name = "rol")
    @Schema(description = "Rol del usuario en el sistema", example = "ADMIN")
    private Role rol;

    public Usuario() {
    }

    public Usuario(String correo, String passwd, Integer cedula, String nombre, boolean cambiarClave, Date fechaUltimoCambioClave, Integer token, Role rol) {
        this.correo = correo;
        this.passwd = passwd;
        this.cedula = cedula;
        this.nombre = nombre;
        this.estado = "Preregistro";
        this.cambiarClave = cambiarClave;
        this.fechaUltimoCambioClave = fechaUltimoCambioClave;
        this.token = token;
        this.intentosFallidos = 0;
        this.rol = rol;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Integer intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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