package br.com.pedroqho.furiabot.model.service;

import org.springframework.stereotype.Service;

@Service
public class MockDataService {

	public String getNextMatch() {
		return """
				
				⚡ Próximo jogo (Mock): 
				FURIA vs NAVI - 20/05 - ESL Pro League
				Local: Dallas, EUA""";
	}
	
	public String getLastMatch() {
		return """ 
				
				🔥 Últimos resultados: (Mock): 
				FURIA 2-1 Vitality
				FURIA 2-0 Apogee
				FURIA 2-1 MIBR""";
	}
	
	public String getPlayers() {
		return """
				🎮 Elenco:
               • KSCERATO (AWP)
               • yuurih (Rifler)
               • FalleN (IGL)
               • chelo (Rifler)
               • drop (Support)
               • sidde (Coach)
               • YENKINDAR (Stand-in)
				""";
	}
	
	public String getAbout() {
		return """
				FURIA é uma equipe CS2 (CS:GO) de esports do Brasil. A FURIA está em 11º lugar no ranking global CS2 (CS:GO) da equipe Tips.gg, com uma pontuação de 3834775 pontos.

				Estatísticas do FURIA:
				O arquivo Tips.gg possui informações sobre 422 jogos de CS2 (CS:GO) com o FURIA. Em 2025, a equipe jogou 21 partidas.
				Ao longo dos últimos 30 dias, a equipe disputou 7 jogos. O FURIA venceu 1 partidas.
				Nos últimos 30 dias, a taxa de vitória de FURIA foi de 14%, o que corresponde a 75% pior da taxa de vitória geral de FURIA (57%).
				Streak atual: Série de derrotas de 3 partidas.
				FURIA está atualmente jogando na seguinte série CS2 (CS:GO): PGL Bucharest 2025.
				Últimas conquistas de FURIA: em 13/10/2024, a equipe conquistou o 3 lugar no IEM Rio 2024 com um prêmio total de $250000.
				Os usuários votaram 1685 vezes nas partidas de FURIA, com 1049 (62.26%) palpites corretas.
				""";
	}
	
	public String getLinks() {
		return """
				Twitter: https://x.com/furia
				Instagram: https://www.instagram.com/furiagg/
				TikTok: https://www.tiktok.com/@furia
				""";
	}
}
