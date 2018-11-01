package br.com.gamesranking.api.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gamesranking.api.model.Jogador;
import br.com.gamesranking.api.repository.JogadorRepository;
import br.com.gamesranking.api.service.JogadorService;
import br.com.gamesranking.api.service.exception.QuantidadeMenorQueZeroException;
import br.com.gamesranking.api.service.exception.QuantidadeVitoriaMaiorQuantidadePartidaException;
import br.com.gamesranking.api.service.exception.UnicidadeNomeException;
import br.com.gamesranking.api.service.impl.JogadorServiceImpl;

@RunWith(SpringRunner.class)
public class JogadorServiceTest {
	
	private static final String NOME = "Sandro";

	private JogadorService jogadorService;
	@MockBean
	private JogadorRepository jogadorRepository;
	private Jogador jogador;
	
	@Before
	public void setUp() throws Exception{
		jogadorService = new JogadorServiceImpl(jogadorRepository);
		
		jogador = new Jogador();
		jogador.setNome(NOME);
		jogador.setQuantidadeVitoria(20);
		jogador.setQuantidadePartida(30);
		
		when(jogadorRepository.findByNome(NOME)).thenReturn(Optional.empty());
	}
	
	@Test
	public void deve_salvar_jogador_no_repositorio() throws Exception {
		jogadorService.salvar(jogador);
		verify(jogadorRepository).save(jogador);
	}
	
	@Test(expected = UnicidadeNomeException.class)
	public void nao_deve_salvar_mais_de_um_jogador_com_o_mesmo_nome() throws Exception {
		when(jogadorRepository.findByNome(NOME)).thenReturn(Optional.of(jogador));
		jogadorService.salvar(jogador);
	}
	
	@Test(expected = QuantidadeVitoriaMaiorQuantidadePartidaException.class)
	public void nao_deve_salvar_quantidade_de_vitoria_maior_que_quantidade_de_partida() throws Exception {
		jogador.setQuantidadeVitoria(100);
		jogadorService.salvar(jogador);
		
		verify(jogadorRepository).save(jogador);
	}
	
	@Test(expected = QuantidadeVitoriaMaiorQuantidadePartidaException.class)
	public void nao_deve_atualizar_quantidade_de_vitoria_maior_que_quantidade_de_partida() throws Exception {
		jogador.setQuantidadeVitoria(100);
		jogadorService.atualizar(jogador);
		
		verify(jogadorRepository).save(jogador);
	}
	
	@Test(expected = QuantidadeMenorQueZeroException.class)
	public void nao_deve_salvar_quantidade_de_vitoria_ou_quantidade_de_partida_menor_que_zero() throws Exception {
		jogador.setQuantidadeVitoria(-5);
		jogador.setQuantidadePartida(-1);
		jogadorService.salvar(jogador);
		
		verify(jogadorRepository).save(jogador);
	}
	
}
