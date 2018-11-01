package br.com.gamesranking.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import br.com.gamesranking.api.model.Jogador;
import br.com.gamesranking.api.repository.JogadorRepository;
import br.com.gamesranking.api.service.JogadorService;
import br.com.gamesranking.api.service.exception.QuantidadeMenorQueZeroException;
import br.com.gamesranking.api.service.exception.QuantidadeVitoriaMaiorQuantidadePartidaException;
import br.com.gamesranking.api.service.exception.UnicidadeNomeException;

@Service
public class JogadorServiceImpl implements JogadorService {
	
	private final JogadorRepository jogadorRepository;
	
	@Autowired
	private MessageSource messageSource;

	public JogadorServiceImpl(JogadorRepository jogadorRepository) {
		this.jogadorRepository = jogadorRepository;
	}
	
	@Override
	public Jogador salvar(Jogador jogador) throws UnicidadeNomeException, QuantidadeVitoriaMaiorQuantidadePartidaException, QuantidadeMenorQueZeroException {
		validarSeExisteJogadorComMesmoNome(jogador);
		validarSeQuantidadeVitoriaMaiorQueQuantidadePartida(jogador);
		validarSeQuantidadeVitoriaOuQueQuantidadePartidaEMenorQueZero(jogador);

		return jogadorRepository.save(jogador);
	}

	private void validarSeQuantidadeVitoriaOuQueQuantidadePartidaEMenorQueZero(Jogador jogador) throws QuantidadeMenorQueZeroException {
		if(jogador.getQuantidadeVitoria() < 0 || jogador.getQuantidadePartida() < 0) {
			throw new QuantidadeMenorQueZeroException(messageSource.getMessage("mensagem.jogador.quantidade.menor.que.zero", null, LocaleContextHolder.getLocale()));
		}
		
	}

	private void validarSeQuantidadeVitoriaMaiorQueQuantidadePartida(Jogador jogador) throws QuantidadeVitoriaMaiorQuantidadePartidaException {
		if(quantidadeVitoriaMaiorQueQuantidadePartida(jogador.getQuantidadeVitoria(), jogador.getQuantidadePartida())) {
			throw new QuantidadeVitoriaMaiorQuantidadePartidaException(messageSource.getMessage("mensagem.vitoria.maior.que.partida", null, LocaleContextHolder.getLocale()));
		}
	}

	private void validarSeExisteJogadorComMesmoNome(Jogador jogador) throws UnicidadeNomeException {
		Optional<Jogador> optionalJogador = jogadorRepository.findByNome(jogador.getNome());
		
		if (optionalJogador.isPresent()) {
			throw new UnicidadeNomeException(messageSource.getMessage("mensagem.jogador.existente", null, LocaleContextHolder.getLocale()));
		}
	}

	private boolean quantidadeVitoriaMaiorQueQuantidadePartida(Integer quantidadeVitoria, Integer quantidadePartida) {
		if(quantidadeVitoria > quantidadePartida) {
			return true;
		}
		return false;
	}
	
	@Override
	public Jogador atualizar(Jogador jogador) throws UnicidadeNomeException, QuantidadeVitoriaMaiorQuantidadePartidaException {
		validarSeQuantidadeVitoriaMaiorQueQuantidadePartida(jogador);
		return jogadorRepository.save(jogador);
	}

	@Override
	public List<Jogador> listar() {
		return jogadorRepository.findAllByOrderByQuantidadeVitoriaDesc();
	}

	@Override
	public Optional<Jogador> carregarJogador(Long id) {
		return jogadorRepository.findById(id);
	}

	@Override
	public void excluir(Long id) {
		jogadorRepository.delete(jogadorRepository.findById(id).get());
	}
	
	

}
