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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

        // Só tenta autenticar se ainda não houver um Authentication no contexto
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // validarToken() deve retornar o e-mail (subject) ou "" se inválido/expirado
            String subject = tokenService.validarToken(token);

            if (subject != null && !subject.isBlank()) {
                UserDetails usuario = usuarioRepository.findByEmail(subject);
                if (usuario != null) {
                    var autenticacao = new UsernamePasswordAuthenticationToken(
                            usuario, null, usuario.getAuthorities());

                    // Anexa detalhes da requisição (IP, sessionId, etc.)
                    autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(autenticacao);

                    // LOG opcional para depurar roles no console
                    // System.out.println("JWT subject=" + subject + " authorities=" + usuario.getAuthorities());
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        String h = request.getHeader("Authorization");
        if (h == null) return null;
        // aceita "Bearer " com qualquer capitalização
        return h.toLowerCase().startsWith("bearer ") ? h.substring(7) : null;
    }
}
