package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.UsuarioAtualizarDto;
import br.com.fiap.calorias.dto.UsuarioCadastroDto;
import br.com.fiap.calorias.dto.UsuarioExibirDto;
import br.com.fiap.calorias.exception.UsuarioNaoEncontradoException;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.beans.Beans;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public UsuarioExibirDto salvar(UsuarioCadastroDto usuarioCadastroDto){
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);


        return new UsuarioExibirDto(usuarioRepository.save(usuario));
    }

//    public Usuario buscarPeloEmail(String email){
//        UserD<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
//
//        if (usuarioOptional.isPresent()){
//            return usuarioOptional.get();
//        }else {
//            throw new RuntimeException("Usuário não encontrado!");
//        }
//    }

    public Usuario buscarPeloNome(String nome){
        Optional<Usuario>usuarioOptional = usuarioRepository.findByNome(nome);

        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }else{
            throw new RuntimeException("Usuário não foi encontrado!");
        }
    }


    public UsuarioExibirDto buscarUsuarioPeloId(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibirDto(usuarioOptional.get());
        }else{
            throw new UsuarioNaoEncontradoException("Usuario não existe !");
        }

    }

    public List<UsuarioExibirDto> listarUsuarios(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibirDto::new)
                .toList();
    }

    public void excluir(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        }else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public Page<UsuarioExibirDto> listAll (Pageable pages){
        return usuarioRepository
                .findAll(pages)
                .map(UsuarioExibirDto::new);
    }

    public UsuarioExibirDto atualizar(UsuarioAtualizarDto usuarioAtualizarDto){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioAtualizarDto, usuario);
        return new UsuarioExibirDto(usuarioRepository.save(usuario));
    }

}
