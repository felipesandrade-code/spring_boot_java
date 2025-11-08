package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.model.UsuarioRole;

public record UsuarioExibirDto(
        Long id,
        String nome,
        String email,
        UsuarioRole role
) {
    public UsuarioExibirDto(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }


}
