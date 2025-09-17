package com.trier.clothestore.Dto.Usuario;

import com.trier.clothestore.Enum.PapelUsuario;

public record RegistrarUsuarioDto(String email, String senha, PapelUsuario papel) {
}
