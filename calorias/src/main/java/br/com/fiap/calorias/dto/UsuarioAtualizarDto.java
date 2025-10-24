package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuario;

public record UsuarioAtualizarDto(
        Long id,
        String nome,
        String email
) {

    public UsuarioAtualizarDto(Usuario usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

}
