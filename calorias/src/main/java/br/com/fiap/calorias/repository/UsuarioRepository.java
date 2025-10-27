package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Usuario;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u.nome from Usuario u where u.nome = :nome")
    public Optional<Usuario> findByNome(@Param("nome") String nome);

    @Query("select u.email from Usuario u where u.email = :email")
    public Optional<Usuario> findByEmail(@Param("email") String email);

}
