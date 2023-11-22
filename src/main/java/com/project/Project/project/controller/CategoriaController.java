package com.project.Project.project.controller;

import com.project.Project.project.model.Categoria;
import com.project.Project.project.service.CategoriaService;
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

@Tag(name = "Categoria", description = "Gestiona las operaciones relacionadas con Categorias")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Crear una nueva categoría", description = "Crea una nueva categoría en el sistema.")
    @ApiResponse(responseCode = "201", description = "Categoría creada satisfactoriamente", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "409", description = "Conflicto: Ya existe una categoría con este nombre", content = @Content(schema = @Schema(implementation = String.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/createCategoria")
    public ResponseEntity<String> createCategoria(@Valid @RequestBody(description = "Datos de la nueva categoría", required = true, content = @Content(schema = @Schema(implementation = Categoria.class))) Categoria categoria) {
        try {
            if (categoriaService.findByName(categoria.getNombreCategorias()).isPresent()) {
                return new ResponseEntity<>("Ya existe una categoría con este nombre", HttpStatus.CONFLICT);
            }

            categoriaService.createCategoria(new Categoria(categoria.getNombreCategorias()));

            return new ResponseEntity<>("Categoria se creó satisfactoriamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ocurrio un error durante el registro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
