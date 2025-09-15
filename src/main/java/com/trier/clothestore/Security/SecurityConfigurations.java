package com.trier.clothestore.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> //como o servidor armazena as infos sobre os users
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //requisicoes independentes contendo infos necessarias para autenticar usuario
                .authorizeHttpRequests(authorize -> authorize //quais requisicoes serao autenticadas
                        .requestMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN") //qualquer request POST para o endpoint "produtos" usuario deve ter Papel admin
                        .requestMatchers(HttpMethod.POST, "/pedidos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/itenspedido").hasRole("ADMIN")
                        .anyRequest().authenticated()) //para o resto das requiscoes sera apenas autenticado
                .build();
    }
}
