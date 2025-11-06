package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Alimento;

public record AlimentoExibirDto(
        Long alimentoId,
        String nome,
        String porcao,
        Double quantidadeProteina,
        Double quantidadeCarboidrato,
        Double quantidadeGorduras,
        Double totalCalorias
) {

    public AlimentoExibirDto(Alimento alimento){
        this(
                alimento.getAlimentoId(),
                alimento.getNome(),
                alimento.getPorcao(),
                alimento.getQuantidadeProteina(),
                alimento.getQuantidadeCarboidrato(),
                alimento.getQuantidadeGorduras(),
                alimento.getTotalCalorias()
        );
    }

}
