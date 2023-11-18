package com.project.Project.project.service;

import com.project.Project.project.model.UsuarioRol;
import com.project.Project.project.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.project.Project.project.model.Usuario;
import com.project.Project.project.model.Rol;
import com.project.Project.project.repository.UsuarioRepository;
import com.project.Project.project.repository.RolRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private ErrorLoggingService errorLoggingService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Usuario usuario = usuarioRepository.findByCorreo(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

            List<GrantedAuthority> authorities = new ArrayList<>();
            Optional<UsuarioRol> roles = usuarioRolRepository.findByIdUsuario(usuario.getId());
            Rol rolName;
            if(roles.isPresent()){
                UsuarioRol rol = roles.get();
                Integer idU = rol.getIdRol();
                rolName = rolRepository.getReferenceById(idU.longValue());
                authorities.add(new SimpleGrantedAuthority(rolName.getRol()));
            }
            return new User(usuario.getCorreo(), usuario.getPasswd(), authorities);
        }catch(Exception e){
            errorLoggingService.logError("Error en CompraController - actualizarEstadoCompra", e, "");
        }
        return null;
    }
}
