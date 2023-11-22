package com.project.Project.project.controller;

import com.project.Project.project.model.Articulo;
import com.project.Project.project.model.ArticuloUpdateValorDTO;
import com.project.Project.project.service.ArticuloService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Tag(name = "Articulos", description = "Gestiona las operaciones relacionadas con articulos")
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {
    @Autowired
    private ArticuloService articuloService;

    @Operation(summary = "Agregar un nuevo artículo", description = "Crea un nuevo artículo. Solo debe ser usado desde compras.")
    @ApiResponse(responseCode = "200", description = "Artículo creado", content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/registrarArticulo")
    public String agregarArticulo(@Valid @RequestBody(description = "Datos del nuevo artículo", required = true, content = @Content(schema = @Schema(implementation = Articulo.class))) Articulo articulo) {
        return "Los articulos deben crearse desde compras.";
    }

    @Operation(summary = "Actualizar el valor unitario de un artículo", description = "Actualiza el valor unitario de un artículo existente.")
    @ApiResponse(responseCode = "200", description = "Valor unitario actualizado exitosamente", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "400", description = "Error: ID de artículo no encontrado")
    @PutMapping("/updateValorUnitario")
    public ResponseEntity<String> updateValorUnitario(@Valid @RequestBody(description = "Datos para actualizar el valor unitario del artículo", required = true, content = @Content(schema = @Schema(implementation = ArticuloUpdateValorDTO.class))) ArticuloUpdateValorDTO updateDTO) {
        if (articuloService.updateValorUnitario(updateDTO.getId(), updateDTO.getValorunitario())) {
            return ResponseEntity.ok("Valor unitario actualizado exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Error: ID de artículo no encontrado.");
        }
    }
}
