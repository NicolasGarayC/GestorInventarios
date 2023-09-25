package com.project.Project.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.Project.project.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT u.correo, r.rol FROM usuarios u LEFT JOIN roles r ON u.id = (SELECT idrol FROM usuario_rol ur WHERE ur.idusuario = :userId) WHERE u.id = :userId and r.id = (SELECT idrol FROM usuario_rol ur WHERE ur.idusuario = :userId);", nativeQuery = true)
    List<Object[]> findUsuariosWithRolId(@Param("userId") int userId);

}