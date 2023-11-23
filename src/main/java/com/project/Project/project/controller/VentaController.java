package com.project.Project.project.controller;

import com.project.Project.project.model.*;
import com.project.Project.project.service.ErrorLoggingService;
import com.project.Project.project.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Tag(name = "Ventas", description = "Gestiona las operaciones relacionadas con Ventas")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ErrorLoggingService errorLoggingService;

    @Operation(summary = "Registrar una nueva venta", description = "Registra una nueva venta en el sistema.")
    @ApiResponse(responseCode = "200", description = "Venta registrada exitosamente", content = @Content)
    @ApiResponse(responseCode = "500", description = "Error al crear la venta", content = @Content)
    @PostMapping("/nuevaVenta")
    public ResponseEntity<Object> createVenta(@Valid @RequestBody VentaArticuloDTO ventaArticuloDTO) {
        try {
            ventaService.createVenta(ventaArticuloDTO);
            return new ResponseEntity<>("Venta registrada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            errorLoggingService.logError("Error en VentaController - createVenta", e, ventaArticuloDTO.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la venta: " + e.getMessage());
        }
    }

    @Operation(summary = "Revertir una venta", description = "Revierte una venta específica.")
    @ApiResponse(responseCode = "200", description = "Venta revertida exitosamente", content = @Content)
    @ApiResponse(responseCode = "500", description = "Error al revertir la venta", content = @Content)
    @PostMapping("/devolucionVenta")
    public ResponseEntity<String> revertirVenta(@Valid @RequestBody ReversionVentaDTO reversionVentaDTO) {
        try {
            boolean exito = ventaService.revertirVenta(reversionVentaDTO.getIdVenta(), reversionVentaDTO.getMotivoReversion(), reversionVentaDTO.getDevuelto(), reversionVentaDTO.isConfirmacionUsuario());
            if (exito) {
                String mensaje = String.format("Articulos devueltos exitosamente. ID de la Venta: %d, Fecha y hora de la reversión: %s",
                        reversionVentaDTO.getIdVenta(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                return new ResponseEntity<>(mensaje, HttpStatus.OK);
            } else {
                // Manejar el caso en que la reversión no fue exitosa
                return new ResponseEntity<>("La reversión de la venta no pudo completarse.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            errorLoggingService.logError("Error en VentaController - devolucionVenta", e, reversionVentaDTO.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar el estado de una venta", description = "Actualiza el estado de los artículos en una venta.")
    @ApiResponse(responseCode = "200", description = "Estado actualizado", content = @Content)
    @ApiResponse(responseCode = "500", description = "Error al actualizar el estado", content = @Content)
    @PostMapping("/estadoVenta")
    public ResponseEntity<String> actualizarEstadoVenta(@RequestBody EstadosDTO estadosDTO) {
        try {
            int idVenta = estadosDTO.getOperacion();
            for(articulosEstadoDTO estado : estadosDTO.getArticulos()){
                ventaService.actualizarEstadoVenta(idVenta,estado);
            }
            return new ResponseEntity<>("Se cambió el estado", HttpStatus.OK);
        } catch (Exception e) {
            errorLoggingService.logError("Error en VentaController - actualizarEstadoCompra", e, "");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/probabilidadAgotarStock")
    public ResponseEntity<List<ProbabilidadAgotarStockDTO>> obtenerProbabilidadAgotarStock() {
        try {
            List<ProbabilidadAgotarStockDTO> probabilidades = ventaService.analizarVentasPorMes();
            if (probabilidades.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(probabilidades);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
