package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.config.security.TokenService;
import br.com.fiap.calorias.dto.LoginDto;
import br.com.fiap.calorias.dto.TokenDTO;
import br.com.fiap.calorias.dto.UsuarioCadastroDto;
import br.com.fiap.calorias.dto.UsuarioExibirDto;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid LoginDto loginDto){
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                    loginDto.email(),
                    loginDto.senha()
            );

            Authentication auth = authenticationManager.authenticate(usernamePassword);

            String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(token));
        }

        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        public UsuarioExibirDto register(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
            UsuarioExibirDto usuarioExibirDto = null;
            usuarioExibirDto = usuarioService.salvar(usuarioCadastroDto);
            return usuarioExibirDto;
        }
}
