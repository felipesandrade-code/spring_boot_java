package br.com.fiap.calorias.controller;


import br.com.fiap.calorias.dto.UsuarioAtualizarDto;
import br.com.fiap.calorias.dto.UsuarioCadastroDto;
import br.com.fiap.calorias.dto.UsuarioExibirDto;
import br.com.fiap.calorias.exception.UsuarioNaoEncontradoException;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class  UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibirDto salvar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        return service.salvar(usuarioCadastroDto);
    }

    @GetMapping("/usuarios/id/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioExibirDto> buscarPorId(@PathVariable Long usuarioId) {
        try {
            return ResponseEntity.ok(service.buscarUsuarioPeloId(usuarioId));
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibirDto> listarUsuarios() {
        return service.listarUsuarios();
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibirDto atualizar(@RequestBody UsuarioAtualizarDto usuarioAtualizarDto) {
        return service.atualizar(usuarioAtualizarDto);
    }

    @GetMapping("/usuarios/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPeloNome(@PathVariable String nome) {
        return service.buscarPeloNome(nome);
    }

//    @GetMapping(value = "/usuarios/email", params ="email")
//    @ResponseStatus(HttpStatus.OK)
//    public Usuario buscarUsuarioPeloEmail(@RequestParam String email) {
//        return service.buscarPeloEmail(email);
//    }

    @GetMapping("/usuarios/paginacao")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibirDto> listAll(Pageable pages){
        return service.listAll(pages);
    }

}
