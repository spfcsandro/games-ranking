package br.com.gamesranking.api.steps;

import br.com.gamesranking.api.model.Jogador;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class AdicionarJogadorSteps {
	
	private Jogador jogador = new Jogador();

	@Dado("^um jogador$")
	public void umJogador() throws Throwable {
	    jogador.setNome("Sandro");
	    jogador.setQuantidadeVitoria(15);
	    jogador.setQuantidadePartida(30);
	}

	@Quando("^adicionar jogador$")
	public void adicionarJogador() throws Throwable {
		System.out.println("Nome do jogador: " + jogador.getNome() + "\n" + "QTD Vitorias: " + jogador.getQuantidadeVitoria() + "\n" + "QTD Partidas: " + jogador.getQuantidadePartida());
	}

	@Então("^sera salvo no banco de dados$")
	public void seraSalvoNoBancoDeDados() throws Throwable {
	    System.out.println("Jogador salvo com sucesso!");
	}
	
	@Quando("^adicionar vitoria$")
	public void adicionarVitoria() throws Throwable {
	    System.out.println("Adicionando vitória para o jogador: " + jogador.getNome());
	}

	@Então("^sera incrementado a vitoria do jogador$")
	public void seraIncrementadoAVitoriaDoJogador() throws Throwable {
		jogador.setQuantidadeVitoria(jogador.getQuantidadeVitoria() + 1); 
	}
	
	@Quando("^adicionar partida$")
	public void adicionarPartida() throws Throwable {
		System.out.println("Adicionando partida para o jogador: " + jogador.getNome());
	}

	@Então("^sera incrementado a partida do jogador$")
	public void seraIncrementadoAPartidaDoJogador() throws Throwable {
		jogador.setQuantidadePartida(jogador.getQuantidadePartida() + 1); 
	}
	
	@Quando("^visualizar ranking$")
	public void visualizarRanking() throws Throwable {
	    System.out.println("******VISUALIZAR RANKING******");
	}

	@Então("^sera exibido todos os jogadores$")
	public void seraExibidoTodosOsJogadores() throws Throwable {
		System.out.println("+-------------------------------+");
		System.out.println("| Jogador | Vitórias | Partidas |");
		System.out.println("|-------------------------------|");
		System.out.println("| " + jogador.getNome() + "  |    " + jogador.getQuantidadeVitoria() + "    |    " + jogador.getQuantidadePartida() + "    |");
		System.out.println("+-------------------------------+");
	}
}
