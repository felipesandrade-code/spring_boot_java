package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuario;

public record UsuarioExibirDto(
        Long id,
        String nome,
        String email
) {
    public UsuarioExibirDto(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }


}
