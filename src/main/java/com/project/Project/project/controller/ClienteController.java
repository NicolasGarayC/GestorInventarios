package com.project.Project.project.controller;

import com.project.Project.project.model.Cliente;
import com.project.Project.project.service.ClienteService;
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

@Tag(name = "Cliente", description = "Gestiona las operaciones relacionadas con cliente")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener cliente por ID", description = "Obtiene un cliente específico por su ID.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado", content = @Content(schema = @Schema(implementation = Cliente.class)))
    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id);
    }

    @Operation(summary = "Guardar un nuevo cliente", description = "Guarda un nuevo cliente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Cliente guardado", content = @Content(schema = @Schema(implementation = Cliente.class)))
    @PostMapping
    public Cliente guardarCliente(@Valid @RequestBody(description = "Datos del cliente a guardar", required = true, content = @Content(schema = @Schema(implementation = Cliente.class))) Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente del sistema por su ID.")
    @ApiResponse(responseCode = "200", description = "Cliente eliminado")
    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }

    @Operation(summary = "Insertar un nuevo cliente", description = "Inserta un nuevo cliente en el sistema y devuelve el cliente guardado.")
    @ApiResponse(responseCode = "201", description = "Cliente creado", content = @Content(schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    @PostMapping("/nuevoCliente")
    public ResponseEntity<Object> insertarCliente(@Valid @RequestBody(description = "Datos del cliente a insertar", required = true, content = @Content(schema = @Schema(implementation = Cliente.class))) Cliente cliente) {
        try {
            Cliente savedCliente = clienteService.guardarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el cliente: " + e.getMessage());
        }
    }
}
