package com.trier.clothestore.Repository;

import com.trier.clothestore.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    UserDetails findByEmail(String email);
}
