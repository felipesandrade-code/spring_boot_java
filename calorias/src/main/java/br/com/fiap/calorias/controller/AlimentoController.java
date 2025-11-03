package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.AlimentoAtualizarDto;
import br.com.fiap.calorias.dto.AlimentoCadastroDto;
import br.com.fiap.calorias.dto.AlimentoExibirDto;
import br.com.fiap.calorias.exception.AlimentoNaoEncontradoException;
import br.com.fiap.calorias.repository.AlimentoRepository;
import br.com.fiap.calorias.service.AlimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;
    @Autowired
    private HttpMessageConverters messageConverters;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentoExibirDto salvar(@RequestBody @Valid AlimentoCadastroDto alimentoCadastroDto){
        return alimentoService.salvarAlimento(alimentoCadastroDto);
    }

    @DeleteMapping("/excluir/{alimentoId}")
    public ResponseEntity<String> excluir(@PathVariable Long alimentoId){
        try {
            alimentoService.excluir(alimentoId);
            return ResponseEntity.ok("Alimento excluído com sucesso!");
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlimentoExibirDto> atualizar(@RequestBody AlimentoAtualizarDto alimentoAtualizarDto){
        try {
            AlimentoExibirDto alimentoExibirDto = alimentoService.atualizar(alimentoAtualizarDto);
            return ResponseEntity.ok(alimentoExibirDto);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("buscar/id/{alimentoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlimentoExibirDto> buscarAlimentoPorId(@PathVariable Long alimentoId){
        try{
            return ResponseEntity.ok(alimentoService.buscarAlimentoPeloId(alimentoId));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibirDto> listarAlimentos(){
        return alimentoService.listarTodosOsAlimentos();
    }

    @GetMapping("buscar/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlimentoExibirDto> buscarAlimentoPeloNome(@PathVariable String nome){
        try{
            return ResponseEntity.ok(alimentoService.buscarAlimentoPeloNome(nome));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/listarCalories", params = {"minimal", "maximal"})
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibirDto>listByTotalCalories(@RequestParam("minimal") double minimal, @RequestParam("maximal") double maximal){
            return alimentoService.listByTotalCalories(minimal, maximal);
    }

    @GetMapping(value = "/buscar/proteina", params = "quantidadeProteina")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AlimentoExibirDto> findByProteina(@RequestParam Double quantidadeProteina){
        try {
            return ResponseEntity.ok(alimentoService.findByQuantidadeDeProteinas(quantidadeProteina));
        }catch (Exception e){
           return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/buscar/carboidrato", params = {"quantidadeCarboidratoAfter", "quantidadeCarboidratoBefore"})
    public ResponseEntity<AlimentoExibirDto> findByCarbsBetween(@RequestParam double quantidadeCarboidratoAfter, @RequestParam double quantidadeCarboidratoBefore){
        try{
            return ResponseEntity.ok(alimentoService.findByQuantidadeDeCarboidratosBetween(quantidadeCarboidratoAfter,quantidadeCarboidratoBefore));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
