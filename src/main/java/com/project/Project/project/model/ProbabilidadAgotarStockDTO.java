package com.project.Project.project.model;

public class ProbabilidadAgotarStockDTO {
    private long idArticulo;
    private String nombreArticulo;
    private double probabilidadAgotarStock;
    private String recomendacionCompra;

    public ProbabilidadAgotarStockDTO(long idArticulo, String nombreArticulo, double probabilidadAgotarStock) {
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
        this.probabilidadAgotarStock = probabilidadAgotarStock;
    }

    public ProbabilidadAgotarStockDTO() {
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public double getProbabilidadAgotarStock() {
        return probabilidadAgotarStock;
    }

    public void setProbabilidadAgotarStock(double probabilidadAgotarStock) {
        this.probabilidadAgotarStock = probabilidadAgotarStock;
    }

    public String getRecomendacionCompra() {
        return recomendacionCompra;
    }

    public void setRecomendacionCompra(String recomendacionCompra) {
        this.recomendacionCompra = recomendacionCompra;
    }

    public ProbabilidadAgotarStockDTO(long idArticulo, String nombreArticulo, double probabilidadAgotarStock, String recomendacionCompra) {
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
        this.probabilidadAgotarStock = probabilidadAgotarStock;
        this.recomendacionCompra = recomendacionCompra;
    }

    @Override
    public String toString() {
        return "ProbabilidadAgotarStockDTO{" +
                "idArticulo=" + idArticulo +
                ", nombreArticulo='" + nombreArticulo + '\'' +
                ", recomendacionCompra='" + recomendacionCompra + '\'' +
                ", probabilidadAgotarStock=" + probabilidadAgotarStock +
                '}';
    }
}
