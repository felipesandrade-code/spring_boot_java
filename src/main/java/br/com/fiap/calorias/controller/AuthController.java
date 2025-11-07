package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.LoginDto;
import br.com.fiap.calorias.dto.UsuarioCadastroDto;
import br.com.fiap.calorias.dto.UsuarioExibirDto;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

        @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid LoginDto loginDto){
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                    loginDto.email(),
                    loginDto.senha()
            );

            Authentication auth = authenticationManager.authenticate(usernamePassword);
            return ResponseEntity.ok().build();
        }

        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity register(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
            UsuarioExibirDto usuarioExibirDto = null;
            usuarioExibirDto = usuarioService.salvar(usuarioCadastroDto);
            return ResponseEntity.ok(usuarioExibirDto);
        }
}
