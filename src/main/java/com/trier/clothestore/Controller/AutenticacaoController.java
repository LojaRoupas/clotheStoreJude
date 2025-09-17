package com.trier.clothestore.Controller;

import com.trier.clothestore.Dto.Usuario.AutenticacaoUsuarioDto;
import com.trier.clothestore.Dto.Usuario.RegistrarUsuarioDto;
import com.trier.clothestore.Model.Usuario;
import com.trier.clothestore.Repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AutenticacaoUsuarioDto usuarioDto){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(usuarioDto.email(), usuarioDto.senha());
        var autenticacao = this.authenticationManager.authenticate(usuarioSenha);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registrar")
    public ResponseEntity registar (@RequestBody @Valid RegistrarUsuarioDto usuarioDto){
        if(this.usuarioRepository.findByEmail(usuarioDto.email()) != null) return ResponseEntity.badRequest().build();

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDto.senha());
        Usuario novoUsuario = new Usuario(usuarioDto.email(), senhaCriptografada, usuarioDto.papel());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
