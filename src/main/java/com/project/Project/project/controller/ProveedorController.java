package com.project.Project.project.controller;

import com.project.Project.project.model.Proveedor;
import com.project.Project.project.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable int id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById((long) id);
        if (proveedor.isPresent()) {
            return ResponseEntity.ok(proveedor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @PostMapping("/createProveedor")
    public ResponseEntity<?> createProveedor(@RequestBody Proveedor proveedor) {
        
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