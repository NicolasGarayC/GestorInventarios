package com.project.Project.project.controller;

import com.project.Project.project.model.CompraArticulosDTO;
import com.project.Project.project.model.DevoUpdateDTO;
import com.project.Project.project.model.EstadosDTO;
import com.project.Project.project.model.articulosEstadoDTO;
import com.project.Project.project.service.CompraService;
import com.project.Project.project.service.ErrorLoggingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Compras", description = "Gestiona las operaciones relacionadas con Compras")
@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private ErrorLoggingService errorLoggingService;
    @Autowired
    private CompraService compraService;

    @Operation(summary = "Registrar una compra", description = "Registra una nueva compra y sus relaciones.")
    @ApiResponse(responseCode = "200", description = "Compra y artículo agregados exitosamente", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Error, parametros incompletos o invalidos", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "402", description = "No está autorizado para esta funcionalidad.", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/registrarCompra")
    public ResponseEntity<String> agregarCompra(@Valid @RequestBody(description = "Datos de la compra y los artículos", required = true, content = @Content(schema = @Schema(implementation = CompraArticulosDTO.class))) CompraArticulosDTO compraArticulosDTO) {
        try {
            compraService.guardarCompraYRelaciones(compraArticulosDTO);
            return new ResponseEntity<>("Compra y artículo agregados exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            errorLoggingService.logError("Error en CompraController - registrarCompra", e, compraArticulosDTO.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar devolución de compra", description = "Actualiza la devolución de una compra.")
    @ApiResponse(responseCode = "200", description = "Devolución exitosa", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Error, parametros incompletos o invalidos", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "402", description = "No está autorizado para esta funcionalidad.", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/devolucionCompra")
    public ResponseEntity<String> actualizarDevolucion(@Valid @RequestBody(description = "Datos de la devolución de la compra", required = true, content = @Content(schema = @Schema(implementation = DevoUpdateDTO.class))) DevoUpdateDTO devoUpdateDTO) {
        try {
            compraService.actualizarDevolucion(devoUpdateDTO.getIdCompra(), devoUpdateDTO.getDescripcion(), devoUpdateDTO.getDevuelto());
            return new ResponseEntity<>("Devolución exitosa", HttpStatus.OK);
        } catch (RuntimeException e) {
            errorLoggingService.logError("Error en CompraController - devolucionCompra", e, devoUpdateDTO.toString());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar estado de compra", description = "Actualiza el estado de los artículos en una compra.")
    @ApiResponse(responseCode = "200", description = "Estado de compra actualizado", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Error, parametros incompletos o invalidos", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "402", description = "No está autorizado para esta funcionalidad.", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/estadoCompra")
    public ResponseEntity<String> actualizarEstadoCompra(@Valid @RequestBody(description = "Datos del estado de los artículos en la compra", required = true, content = @Content(schema = @Schema(implementation = EstadosDTO.class))) EstadosDTO estadosDTO) {
        try {
            int idCompra = estadosDTO.getOperacion();
            for(articulosEstadoDTO estado : estadosDTO.getArticulos()){
                compraService.actualizarEstadoCompra(idCompra, estado);
            }
            return new ResponseEntity<>("Se cambió el estado", HttpStatus.OK);
        } catch (Exception e) {
            errorLoggingService.logError("Error en CompraController - actualizarEstadoCompra", e, "");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
