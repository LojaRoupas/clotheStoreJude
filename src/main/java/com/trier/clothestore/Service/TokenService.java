package com.trier.clothestore.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.trier.clothestore.Model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    //app properties
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try{
            // Cria um algoritmo de assinatura HS256 (HMAC com SHA-256) usando sua chave secreta.
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("clothestore") //criador do token
                    .withSubject(usuario.getEmail()) //usuario que recebe o token
                    .withExpiresAt(genExpirationDate()) //tempo de expirar
                    .sign(algoritmo); //assinatura e geracao final
            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro durante geração do token.", exception);
        }
    }

    public String validarToken (String token){
        try{
            // Cria o mesmo algoritmo HMAC256 com a MESMA chave secreta usada para assinar.
            //algoritmo criptográfico que garante a integridade e a autenticidade de uma mensagem
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("clothestore")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        //pega tempo atual - adiciona duas horas - transforma no Instant - coloca no time zone Brasilia
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
