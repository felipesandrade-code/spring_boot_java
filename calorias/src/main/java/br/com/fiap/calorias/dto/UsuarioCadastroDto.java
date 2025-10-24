package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuario;

public record UsuarioCadastroDto(
        Long id,
        String nome,
        String email,
        String senha
) {

    public UsuarioCadastroDto(Usuario usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }

}
