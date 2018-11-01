# language: pt

Funcionalidade: Adicionar jogador
	Como um usuário
	Eu quero cadastrar um jogador e permitir informar nome, quantidadeVitorias e quantidadePartidas
	Para controlar o Ranking dos jogos
	

Cenário: Adicionar um jogador com sucesso
	Dado um jogador
	Quando adicionar jogador
	Então sera salvo no banco de dados
	
Cenário: Adicionar vitória para um jogador
	Dado um jogador
	Quando adicionar vitoria
	Então sera incrementado a vitoria do jogador	
	
Cenário: Adicionar partida para um jogador
	Dado um jogador
	Quando adicionar partida
	Então sera incrementado a partida do jogador	
	
Cenário: Visualizar Ranking dos jogadores
	Dado um jogador
	Quando visualizar ranking
	Então sera exibido todos os jogadores	
	
	
	
	