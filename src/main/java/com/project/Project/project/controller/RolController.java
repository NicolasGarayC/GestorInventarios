package com.project.Project.project.controller;

import com.project.Project.project.model.Rol;
import com.project.Project.project.service.RolService;
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

import java.util.List;
import java.util.Optional;

@Tag(name = "Roles", description = "Gestiona las operaciones relacionadas con Roles")
@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @Operation(summary = "Obtener todos los roles", description = "Obtiene una lista de todos los roles disponibles.")
    @ApiResponse(responseCode = "200", description = "Lista de roles", content = @Content(schema = @Schema(implementation = Rol.class)))
    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Obtener rol por ID", description = "Obtiene un rol específico por su ID.")
    @ApiResponse(responseCode = "200", description = "Rol encontrado", content = @Content(schema = @Schema(implementation = Rol.class)))
    @ApiResponse(responseCode = "404", description = "Rol no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Long id) {
        Optional<Rol> rol = rolService.getRolById(id);
        return rol.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo rol", description = "Crea un nuevo rol en el sistema.")
    @ApiResponse(responseCode = "200", description = "Rol creado", content = @Content(schema = @Schema(implementation = Rol.class)))
    @PostMapping
    public ResponseEntity<Rol> createRol(@Valid @RequestBody(description = "Datos del rol", required = true, content = @Content(schema = @Schema(implementation = Rol.class))) Rol rol) {
        Rol createdRol = rolService.createRol(rol);
        return ResponseEntity.ok(createdRol);
    }
}
