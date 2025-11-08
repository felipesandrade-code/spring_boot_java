package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(@Param("email") String email);

    @Query("select u.nome from Usuario u where u.nome = :nome")
    Optional<Usuario> findByNome(@Param("nome") String nome);

//    @Query("SELECT u.email from Usuario u WHERE u.email = :email")
//    Optional<Usuario> findByEmail(@Param("email") String email);

}
