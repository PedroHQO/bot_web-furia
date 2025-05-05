package br.com.pedroqho.furiabot.model.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class HltvScraperService {
	
	public String getNextMatch() throws IOException{
		try {
			Document doc = Jsoup.connect("https://www.hltv.org/team/8297/furia")
					.userAgent("Mozilla/5.0")
					.timeout(10000)
					.get();
			
			Element nextMatch = doc.selectFirst(".upcomingMatch[team=8297]");
			return nextMatch != null ?
					"Próximo jogo (HLTV): " + nextMatch.text() :
						"Sem jogos agendados";
		}catch(Exception e){
			throw new IOException("HLTV indisponível");
		}
	}
	
	public String getLastResults() throws IOException{
		try {
			Document doc = Jsoup.connect("https://www.hltv.org/results?team=8297")
					.userAgent("Mozilla/5.0")
					.get();
			
			Element result = doc.selectFirst(".result-con .match-info");
			return result != null ?
					"Último resultado (HLTV): " + result.text() :
						"Nenhum resultado recente";
		}catch(Exception e) {
			throw new IOException("Erro ao buscar resultados");
		}
	}
	
}
