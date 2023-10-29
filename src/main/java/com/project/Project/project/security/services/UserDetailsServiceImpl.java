package com.project.Project.project.security.services;

import com.project.Project.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Project.project.model.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UsuarioRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user = userRepository.findByCorreo(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

    return UserDetailsImpl.build(user);
  }

}
