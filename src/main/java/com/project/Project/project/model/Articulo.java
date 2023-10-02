package com.project.Project.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombrearticulo;
    private String marca;
    private String modelo;
    private String color;
    private String unidaddemedida;

    private int unidadesdisponibles;
    private double valorunitario;

    public Articulo() {
    }

    public Articulo(String nombrearticulo, String marca, String modelo, String color, String unidaddemedida, int unidadesdisponibles, double valorunitario) {
        this.nombrearticulo = nombrearticulo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.unidaddemedida = unidaddemedida;
        this.unidadesdisponibles = unidadesdisponibles;
        this.valorunitario = valorunitario;
    }

    public String getNombrearticulo() {
        return nombrearticulo;
    }

    public void setNombrearticulo(String nombrearticulo) {
        this.nombrearticulo = nombrearticulo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUnidaddemedida() {
        return unidaddemedida;
    }

    public void setUnidaddemedida(String unidaddemedida) {
        this.unidaddemedida = unidaddemedida;
    }

    public int getUnidadesdisponibles() {
        return unidadesdisponibles;
    }

    public void setUnidadesdisponibles(int unidadesdisponibles) {
        this.unidadesdisponibles = unidadesdisponibles;
    }

    public double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }
}