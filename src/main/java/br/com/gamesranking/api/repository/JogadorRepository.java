package br.com.gamesranking.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gamesranking.api.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

	Optional<Jogador> findByNome(String nome);
	
	List<Jogador> findAllByOrderByQuantidadeVitoriaDesc();

}
