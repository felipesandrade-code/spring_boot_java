package br.com.fiap.calorias.controller;


import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario){
        return service.salvar(usuario);
    }

    @GetMapping("/usuarios/id/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarPorId(@PathVariable Long usuarioId){
        return service.buscarUsuarioPeloId(usuarioId);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listarUsuarios(){
        return service.listarUsuarios();
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
       service.excluir(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario){
        return service.atualizar(usuario);
    }

    @GetMapping("/usuarios/nome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPeloNome(@PathVariable String nome){
        return service.buscarPeloNome(nome);
    }

    @GetMapping("/usuarios/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscarUsuarioPeloEmail(@PathVariable String email){
        return service.buscarPeloEmail(email);
    }

}
