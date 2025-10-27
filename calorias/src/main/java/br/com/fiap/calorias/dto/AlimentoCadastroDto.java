package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Alimento;
import jakarta.validation.constraints.*;
import org.aspectj.bridge.Message;

public record AlimentoCadastroDto(
        Long alimentoId,
        @NotBlank(message = "O nome do alimento não pode ser vazio.")
        String nome,

        @NotEmpty(message = "A porção do alimento que você está cadastrando não pode ser vazio")
        @Size(min = 1, max = 4, message = "A porção deve ser informada entre 1 e 4 caracteres.")
        String porcao,

        @NotNull(message = "A quantidade de proteína deve ser informada.")
        @Digits(integer = 5, fraction = 2, message = "A quantidade de proteína deve ter no máximo 2 casas decimais")
        @PositiveOrZero
        double quantidadeProteina,

        @NotNull(message = "A quantidade de carboidrato deve ser informada.")
        @Digits(integer = 5, fraction = 2, message = "A quantidade de carboidrato deve ter no máximo 2 casas decimais")
        @PositiveOrZero
        double quantidadeCarboidrato,

        @NotNull(message = "A quantidade de gordura deve ser informada.")
        @Digits(integer = 5, fraction = 2, message = "A quantidade de gordura deve ter no máximo 2 casas decimais")
        @PositiveOrZero
        double quantidadeGorduras
) {
    public AlimentoCadastroDto(Alimento alimento){
        this(
                alimento.getAlimentoId(),
                alimento.getNome(),
                alimento.getPorcao(),
                alimento.getQuantidadeProteina(),
                alimento.getQuantidadeCarboidrato(),
                alimento.getQuantidadeGorduras()
        );
    }
}
