package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    public Optional<Alimento> findByNome(String nome);

    public Optional<Alimento> findById(Long id);

}
