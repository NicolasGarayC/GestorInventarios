package com.project.Project.project.model;


public class VentasPorArticuloYMesDTO {
    private long idArticulo;
    private String nombreArticulo;
    private int mes;
    private int año;
    private long totalUnidadesVendidas;

    public VentasPorArticuloYMesDTO(int idArticulo, String nombreArticulo, int mes, int año, long totalUnidadesVendidas) {
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
        this.mes = mes;
        this.año = año;
        this.totalUnidadesVendidas = totalUnidadesVendidas;
    }

    public VentasPorArticuloYMesDTO() {
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public long getTotalUnidadesVendidas() {
        return totalUnidadesVendidas;
    }

    public void setTotalUnidadesVendidas(long totalUnidadesVendidas) {
        this.totalUnidadesVendidas = totalUnidadesVendidas;
    }
}

