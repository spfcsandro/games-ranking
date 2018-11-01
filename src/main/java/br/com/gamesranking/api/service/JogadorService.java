package br.com.gamesranking.api.service;

import java.util.List;
import java.util.Optional;

import br.com.gamesranking.api.model.Jogador;
import br.com.gamesranking.api.service.exception.QuantidadeMenorQueZeroException;
import br.com.gamesranking.api.service.exception.QuantidadeVitoriaMaiorQuantidadePartidaException;
import br.com.gamesranking.api.service.exception.UnicidadeNomeException;

public interface JogadorService{

	Jogador salvar(Jogador jogador) throws UnicidadeNomeException, QuantidadeVitoriaMaiorQuantidadePartidaException, QuantidadeMenorQueZeroException;

	Jogador atualizar(Jogador jogador) throws UnicidadeNomeException, QuantidadeVitoriaMaiorQuantidadePartidaException;

	List<Jogador> listar();

	Optional<Jogador> carregarJogador(Long id);

	void excluir(Long id);
}
