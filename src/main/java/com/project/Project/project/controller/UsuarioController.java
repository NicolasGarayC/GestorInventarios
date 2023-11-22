package com.project.Project.project.controller;
import com.project.Project.project.model.Role;
import com.project.Project.project.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.project.Project.project.repository.UsuarioRepository;
import com.project.Project.project.service.TokenGenerator;
import com.project.Project.project.service.UsuarioService;
import com.project.Project.project.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Usuarios", description = "Gestiona las operaciones relacionadas con Usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario específico por su ID.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado", content = @Content(schema = @Schema(implementation = Usuario.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    @GetMapping("/getusuario/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Insertar un nuevo usuario", description = "Inserta un nuevo usuario en el sistema.")
    @ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content)
    @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario ya existe", content = @Content)
    @PostMapping("/insertarUsuario")
    public ResponseEntity<String> insertarUsuario(@RequestBody(description = "Datos del nuevo usuario", required = true, content = @Content(schema = @Schema(implementation = Map.class))) Map<String, Object> usuarioData) {
        String correo = (String) usuarioData.get("correo");
        String passwd = (String) usuarioData.get("passwd");
        int cedula = (int) usuarioData.get("cedula");
        String nombre = (String) usuarioData.get("nombre");
        boolean cambiarClave = (boolean) usuarioData.get("cambiarClave");
        Date fechaUltimoCambioClave = new Date();
        Role rol;
        if(((String) usuarioData.get("rol")).equals("ADMIN")){
            rol = Role.ADMIN;
        }else if(((String) usuarioData.get("rol")).equals("OPERATIVO")){
            rol = Role.OPERATIVO;
        }else if(((String) usuarioData.get("rol")).equals("AUDITOR")){
            rol = Role.AUDITOR;
        }else {
            rol = Role.OPERATIVO;
        }

        if (usuarioRepository.existsByCorreo(correo)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está en uso.");
        }

        if (usuarioRepository.existsByCedula(cedula)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cédula ya está en uso.");
        }

        String valid = usuarioService.validarContrasena(passwd);
        if(!(valid.equals("ok"))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + valid);
        }
        int token = tokenGenerator.generateToken();
        try{
            emailService.sendSimpleMessage(correo,"Token Registro Gestion de Inventarios","Este es su token de confirmación de registro, ingreselo en la aplicación: " + token);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo insertar el usuario: " + e.getMessage());
        }

        try {
            Usuario usuario = new Usuario(correo, passwd, cedula, nombre, cambiarClave, fechaUltimoCambioClave, token, rol);
            usuarioService.insertarUsuario(usuario);


            return ResponseEntity.status(HttpStatus.CREATED).body("Se ha registrado con éxito. Al correo sumistrado llegará un token de verificación para activar su cuenta");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo insertar el usuario: " + e.getMessage());
        }
    }

    @Operation(summary = "Validar usuario", description = "Valida las credenciales de un usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario autenticado", content = @Content)
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content)
    @PostMapping("/authUsuario")
    public ResponseEntity<String> validarUsuario(@RequestBody(description = "Credenciales del usuario", required = true, content = @Content(schema = @Schema(implementation = Map.class))) Map<String, Object> credenciales) {
        try {
            String correo = (String) credenciales.get("correo");
            String passwd = (String) credenciales.get("passwd");

            if (usuarioService.validarUsuario(correo, passwd)) {
                return ResponseEntity.ok("Usuario Autenticado.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Confirmar registro de usuario", description = "Confirma el registro de un usuario mediante un token.")
    @ApiResponse(responseCode = "200", description = "Usuario confirmado", content = @Content)
    @ApiResponse(responseCode = "401", description = "Token inválido o usuario no encontrado", content = @Content)
    @PostMapping("/confirmarregistro")
    public ResponseEntity<String> confirmarRegistro(@RequestBody(description = "Datos para confirmar registro", required = true, content = @Content(schema = @Schema(implementation = Map.class))) Map<String, Object> credenciales) {
        try {
            String correo = (String) credenciales.get("correo");
            Integer token = (Integer) credenciales.get("token");

            if (usuarioService.confirmarRegistro(correo, token)) {
                return ResponseEntity.ok("Usuario Confirmado.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
        }
    }

    @Operation(summary = "Recuperar contraseña", description = "Inicia el proceso de recuperación de contraseña para un usuario.")
    @ApiResponse(responseCode = "200", description = "Correo de recuperación enviado", content = @Content)
    @ApiResponse(responseCode = "400", description = "Correo no proporcionado o inválido", content = @Content)
    @PostMapping("/correoReestablecerContrasenia")
    public ResponseEntity<String> recuperarContrasenia(@RequestBody(description = "Correo electrónico del usuario", required = true, content = @Content(schema = @Schema(implementation = Map.class))) Map<String, String> body) {
        String correo= body.get("correo");
        if(correo!= null){
            try{
                usuarioService.correoRecuperacionContrasenia(correo);
                return ResponseEntity.ok("Se ha enviado un correo de recuperacion con su token a su dirección de email registrada.");
            }catch(Exception e){
                return ResponseEntity.badRequest().body("Error: "+ e);
            }
        }else{
            return ResponseEntity.badRequest().body("Correo inexistente.");
        }
    }

    @Operation(summary = "Reestablecer contraseña", description = "Reestablece la contraseña de un usuario mediante un token.")
    @ApiResponse(responseCode = "200", description = "Contraseña reestablecida", content = @Content)
    @ApiResponse(responseCode = "400", description = "Token inválido o datos incorrectos", content = @Content)
    @PostMapping("/ReestablecerContrasenia/{numeroToken}")
    public ResponseEntity<String> reestablecerContrasenia(@PathVariable("numeroToken") int numeroToken, @RequestBody(description = "Nueva contraseña y token", required = true, content = @Content(schema = @Schema(implementation = Map.class))) Map<String, String> body) {
        String contrasenia = body.get("contrasenia");
        if (String.valueOf(numeroToken).length() == 6) {
            String resultado = usuarioService.recuperarContrasenia(numeroToken, contrasenia);
            if (resultado.equals("Contraseña actualizada con éxito.")) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.badRequest().body(resultado);
            }
        } else {
            return ResponseEntity.badRequest().body("El token debe ser de seis dígitos.");
        }
    }

    @Operation(summary = "Rehabilitar usuario", description = "Rehabilita un usuario mediante un token.")
    @ApiResponse(responseCode = "200", description = "Usuario rehabilitado", content = @Content)
    @ApiResponse(responseCode = "400", description = "Token inválido o datos incorrectos", content = @Content)
    @PostMapping("/RehabilitarUsuario/{numeroToken}")
    public ResponseEntity<String> rehabilitarUsuario(@PathVariable("numeroToken") int numerotoken, @RequestBody(description = "Nueva contraseña y token", required = true, content = @Content(schema = @Schema(implementation = Map.class))) Map<String, String> body) {
        String contrasenia = body.get("contrasenia");
        try{
            String resultado = usuarioService.recuperarContrasenia(numerotoken,contrasenia);
            if (resultado.equals("Contraseña actualizada con éxito.")) {
                return ResponseEntity.ok("Usuario reestablecido.");
            } else {
                return ResponseEntity.badRequest().body(resultado);
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Token invalido.");
        }
    }
}