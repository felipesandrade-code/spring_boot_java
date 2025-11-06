package br.com.fiap.calorias.repository;

import br.com.fiap.calorias.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    @Query("SELECT a FROM Alimento a WHERE a.nome = :nome")
     Optional<Alimento> findByNome(@Param("nome") String nome);

    @Query("SELECT a FROM Alimento a WHERE a.alimentoId = :id")
     Optional<Alimento> findById(@Param("id") Long id);

    @Query("SELECT a from Alimento a WHERE a.totalCalorias between :minimo and :maximo ORDER BY a.totalCalorias desc")
    List<Alimento> listPerTotalCalories(@Param("minimo") double minimo, @Param("maximo") double maximo);

    Optional<Alimento> findByQuantidadeProteina(Double quantidadeProteina);

    Optional<Alimento> findByQuantidadeCarboidratoBetween(Double quantidadeCarboidratoAfter, Double quantidadeCarboidratoBefore);

}
