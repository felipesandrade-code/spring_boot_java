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
}
