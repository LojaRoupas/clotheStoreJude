package com.trier.clothestore.Model;

import com.trier.clothestore.Enum.PapelUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuario")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String email;
    private String senha;
    private PapelUsuario papel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.papel == PapelUsuario.ADMIN){
            return List.of(new SimpleGrantedAuthority("PAPEL_ADMIN"), new SimpleGrantedAuthority("PAPEL_USUARIO"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("PAPEL_USUARIO"));
        }
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
