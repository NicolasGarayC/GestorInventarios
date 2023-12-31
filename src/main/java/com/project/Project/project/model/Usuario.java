package com.project.Project.project.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "correo", length = 45, nullable = false)
    private String correo;

    @Column(name = "passwd", length = 45, nullable = false)
    private String passwd;

    @Column(name = "cedula", length = 11)
    private int cedula;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "estado", length = 45)
    private String estado;

    @Column(name = "cambiarclave")
    private boolean cambiarClave;


    @Column(name = "fecha_ult_cambio_clave")
    private Date fechaUltimoCambioClave;

    @Column(name = "token")
    private Integer token;

    @Column(name = "intentos_fallidos")
    private Integer intentosFallidos;

    public Usuario() {
    }

    public Usuario(String correo, String passwd, Integer cedula, String nombre, boolean cambiarClave, Date fechaUltimoCambioClave, Integer token) {
        this.correo = correo;
        this.passwd = passwd;
        this.cedula = cedula;
        this.nombre = nombre;
        this.estado = "Preregistro";
        this.cambiarClave = cambiarClave;
        this.fechaUltimoCambioClave = fechaUltimoCambioClave;
        this.token = token;
        this.intentosFallidos = 0;
    }

    public Integer getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(Integer intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
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