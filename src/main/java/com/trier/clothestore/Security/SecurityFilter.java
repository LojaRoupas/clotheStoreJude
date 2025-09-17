package com.trier.clothestore.Security;

import com.trier.clothestore.Repository.UsuarioRepository;
import com.trier.clothestore.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String subject = tokenService.validarToken(token); // email ou "" se invalido

            if (subject != null && !subject.isBlank()) {       //checa token valido
                UserDetails usuario = usuarioRepository.findByEmail(subject);
                if (usuario != null) {                         // checa usuario existente
                    var autenticacao = new UsernamePasswordAuthenticationToken(
                            usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        String h = request.getHeader("Authorization");
        if (h == null) return null;
        return h.toLowerCase().startsWith("bearer ") ? h.substring(7) : null; // aceita "Bearer " ou "bearer "
    }
}
