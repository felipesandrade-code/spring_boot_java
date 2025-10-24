package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.AlimentoAtualizarDto;
import br.com.fiap.calorias.dto.AlimentoCadastroDto;
import br.com.fiap.calorias.dto.AlimentoExibirDto;
import br.com.fiap.calorias.model.Alimento;
import br.com.fiap.calorias.repository.AlimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public AlimentoExibirDto salvarAlimento(AlimentoCadastroDto alimentoDTO){
        Alimento alimento = new Alimento();
        BeanUtils.copyProperties(alimentoDTO, alimento);

        alimento.setTotalCalorias(
                calcularCalorias(
                        alimento.getQuantidadeProteina(),
                        alimento.getQuantidadeCarboidrato(),
                        alimento.getQuantidadeGorduras()
                )
        );

        Alimento alimentoSalvo = alimentoRepository.save(alimento);
        return new AlimentoExibirDto(alimentoSalvo);
    }

    public AlimentoExibirDto salvar(AlimentoCadastroDto alimentoCadastroDto){
        Alimento alimento = new Alimento();
        BeanUtils.copyProperties(alimentoCadastroDto, alimento);
        return new AlimentoExibirDto(alimentoRepository.save(alimento));
    }

    public AlimentoExibirDto atualizar(AlimentoAtualizarDto alimentoAtualizarDto){
        Optional<Alimento>alimentoOptional = alimentoRepository.findById(alimentoAtualizarDto.alimentoId());

        if(alimentoOptional.isPresent()){
            Alimento alimento = new Alimento();
            BeanUtils.copyProperties(alimentoAtualizarDto,alimento);

            alimento.setTotalCalorias(
                    calcularCalorias(
                            alimento.getQuantidadeProteina(),
                            alimento.getQuantidadeCarboidrato(),
                            alimento.getQuantidadeGorduras()
                    )
            );
            return new AlimentoExibirDto(alimentoRepository.save(alimento));
        } else {
            throw new RuntimeException("Alimento não encontrado!");
        }
    }

    public void excluir(Long alimentoId){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(alimentoId);
        if (alimentoOptional.isPresent()){
            alimentoRepository.delete(alimentoOptional.get());
        } else{
            throw new RuntimeException("Não possui nenhum alimento com esse Id para você excluir!");
        }
    }

    public AlimentoExibirDto buscarAlimentoPeloId(Long alimentoId){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(alimentoId);
        if (alimentoOptional.isPresent()){
            return new AlimentoExibirDto(alimentoOptional.get());
        } else{
            throw new RuntimeException("Não há nenhum alimento desse Id");
        }
    }

    public AlimentoExibirDto buscarAlimentoPeloNome(String nome){
        Optional<Alimento> alimentoOptional = alimentoRepository.findByNome(nome);
        if (alimentoOptional.isPresent()){
            return new AlimentoExibirDto(alimentoOptional.get());
        }else{
            throw new RuntimeException("Não possui nenhum alimento deste nome em nosso banco de dados.");
        }
    }

    public List<AlimentoExibirDto> listarTodosOsAlimentos(){
        return alimentoRepository
                .findAll()
                .stream()
                .map(AlimentoExibirDto::new)
                .toList();
    }

    public Double calcularCalorias (Double proteinas, Double carboidratos, Double gorduras){
        Double calorias = (proteinas * 4) + (carboidratos * 4) + (gorduras * 9);
        return calorias;
    }

}
