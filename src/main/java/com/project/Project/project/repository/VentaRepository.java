package com.project.Project.project.repository;

import com.project.Project.project.model.Venta;
import com.project.Project.project.model.VentasPorArticuloYMesDTO;
import com.project.Project.project.model.VentasPorArticuloYMesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Modifying
    @Query(value = "INSERT INTO venta_usuario (idventa, idusuario) VALUES (:idVenta, :idUsuario)", nativeQuery = true)
    void insertVentaUsuario(@Param("idVenta") int idVenta, @Param("idUsuario") int idUsuario);

    @Modifying
    @Query(value = "INSERT INTO venta_cliente (idventa, idcliente) VALUES (:idVenta, :idCliente)", nativeQuery = true)
    void insertVentaCliente(@Param("idVenta") int idVenta, @Param("idCliente") int idCliente);

    @Query(value = "SELECT a.id, a.nombrearticulo, MONTH(v.fechaventa) AS mes, YEAR(v.fechaventa) AS anio, SUM(d.unidadesvendidas) AS total " +
            "FROM detalle_venta d " +
            "JOIN ventas v ON d.idventa = v.id " +
            "JOIN articulos a ON d.idarticulo = a.id " +
            "WHERE MONTH(v.fechaventa) = :mes " +
            "GROUP BY a.id, MONTH(v.fechaventa), YEAR(v.fechaventa)",
            nativeQuery = true)
    List<VentasPorArticuloYMesProjection> findVentasPorMesHistorico(@Param("mes") int mes);
}
