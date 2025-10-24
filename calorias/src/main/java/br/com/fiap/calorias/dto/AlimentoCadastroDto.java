package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Alimento;

public record AlimentoCadastroDto(
        Long alimentoId,
        String nome,
        String porcao,
        Double quantidadeProteina,
        Double quantidadeCarboidrato,
        Double quantidadeGorduras
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
