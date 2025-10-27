package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
        Long id,

        @NotBlank(message = "O nome do usuário deve ser informado!")
        String nome,

        @NotBlank(message = "O e-mail do usuário deve ser informado!")
        @Email(message = "O e-mail foi inserido incorretamente.")
        String email,

        @NotBlank(message = "A senha deve ser informada para o cadastro do usuário.")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 10 caracteres.")
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
