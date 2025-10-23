package br.com.fiap.calorias.service;

import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.repository.UsuarioRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPeloEmail(String email){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public Usuario buscarPeloNome(String nome){
        Optional<Usuario>usuarioOptional = usuarioRepository.findByNome(nome);

        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }else{
            throw new RuntimeException("Usuário não foi encontrado!");
        }
    }


    public Usuario buscarUsuarioPeloId(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }else{
            throw new RuntimeException("Usuario não encontrado!");
        }
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public void excluir(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        }else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public Usuario atualizar(Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuario.getUsuarioId());

        if (usuarioOptional.isPresent()){
           return usuarioRepository.save(usuario);
        }else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }

}
