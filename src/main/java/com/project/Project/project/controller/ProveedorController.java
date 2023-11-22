package com.project.Project.project.controller;

import com.project.Project.project.model.Proveedor;
import com.project.Project.project.repository.ProveedorRepository;
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

import java.util.List;
import java.util.Optional;

@Tag(name = "Proveedores", description = "Gestiona las operaciones relacionadas con proveedores")
@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Operation(summary = "Obtener proveedor por ID", description = "Obtiene un proveedor específico por su ID.")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado", content = @Content(schema = @Schema(implementation = Proveedor.class)))
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable int id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById((long) id);
        if (proveedor.isPresent()) {
            return ResponseEntity.ok(proveedor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtener todos los proveedores", description = "Obtiene una lista de todos los proveedores.")
    @ApiResponse(responseCode = "200", description = "Lista de proveedores", content = @Content(schema = @Schema(implementation = Proveedor.class)))
    @GetMapping("/all")
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Operation(summary = "Crear un nuevo proveedor", description = "Crea un nuevo proveedor en el sistema.")
    @ApiResponse(responseCode = "201", description = "Proveedor creado", content = @Content)
    @ApiResponse(responseCode = "409", description = "Conflicto: el proveedor ya existe", content = @Content)
    @PostMapping("/createProveedor")
    public ResponseEntity<?> createProveedor(@Valid @RequestBody(description = "Datos del proveedor", required = true, content = @Content(schema = @Schema(implementation = Proveedor.class))) Proveedor proveedor) {
        Proveedor existingProveedor = proveedorRepository.findByIdentificacion(proveedor.getIdentificacion());
        if (existingProveedor != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El proveedor ya se encuentra registrado.");
        } else {
            Proveedor newProveedor = proveedorRepository.save(proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        }
    }
}
