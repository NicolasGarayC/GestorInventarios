package com.project.Project.project.controller;
import com.project.Project.project.model.Usuario;
import com.project.Project.project.model.UsuarioDAO;
import com.project.Project.project.model.UsuarioRol;
import com.project.Project.project.payload.request.LoginRequest;
import com.project.Project.project.payload.response.UserInfoResponse;
import com.project.Project.project.repository.UsuarioRepository;
import com.project.Project.project.repository.UsuarioRolRepository;
import com.project.Project.project.security.services.UserDetailsImpl;
import com.project.Project.project.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.project.Project.project.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {


    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @GetMapping("/getusuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/usuariosNativo/{userId}")
    public List<Object[]> getUsuariosWithRol(@PathVariable int userId) {
        List<Object[]> usuariosConRol = usuarioRepository.findUsuariosWithRolId(userId);
        return usuariosConRol;
    }

    @PostMapping("/insertarUsuario")
    public ResponseEntity<Map<String, Object>> insertarUsuario(@RequestBody Map<String, Object> usuarioData) {
        Map<String, Object> response = new HashMap<>();

        String correo = (String) usuarioData.get("correo");
        String passwd = (String) usuarioData.get("passwd");
        int cedula = (int) usuarioData.get("cedula");
        String nombre = (String) usuarioData.get("nombre");
        String estado = (String) usuarioData.get("estado");
        boolean cambiarClave = (boolean) usuarioData.get("cambiarClave");
        Date fechaUltimoCambioClave = new Date();

        int idRol = (int) usuarioData.get("idRol");

        if (usuarioRepository.existsByCorreo(correo)) {
            response.put("error", "El correo ya está en uso.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (usuarioRepository.existsByCedula(cedula)) {
            response.put("error", "La cédula ya está en uso.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Usuario usuario = new Usuario(correo, passwd, cedula, nombre, estado, cambiarClave, fechaUltimoCambioClave);
            UsuarioDAO nuevoUsuario = usuarioService.insertarUsuario(usuario);

            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setIdUsuario(nuevoUsuario.getId());
            usuarioRol.setIdRol(idRol);
            usuarioRolRepository.save(usuarioRol);

            response.put("message", "Usuario insertado con éxito. ID: " + nuevoUsuario.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("error", "No se pudo insertar el usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }
}