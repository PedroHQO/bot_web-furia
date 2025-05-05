package br.com.pedroqho.furiabot.model.service;

import java.io.IOException;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

@Service
public class FuriaNewsService {
	private static final Logger logger = LoggerFactory.getLogger(FuriaNewsService.class);
	
	@Autowired
	private MockDataService mockService;
	
	public String getNextMatchFromLiquipedia() throws IOException {
		try {
			
			Document doc = Jsoup.connect("https://liquipedia.net/counterstrike/FURIA")
					.userAgent("Mozilla/5.0 Chrome/91.0.4472.124")
					.timeout(15000)
					.get();
			
			Elements matches = doc.select(".matches-table .match");
			if(!matches.isEmpty()) {
				String matchInfo = matches.first().text();
				logger.info("Dados obtidos do Liquipedia: {}", matchInfo);
				return "Próximo jogo: "  + matchInfo;
 			}			
			
		}catch(Exception e) {
			logger.warn("Falha no Liquipedia: {}", e.getMessage());
		}
		
		logger.warn("Retornando dados mockados");
		return mockService.getNextMatch();
		
	}
	
	public String getLastMatchFromHltvApi() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String json = restTemplate.getForObject(
					"https://hltv-api.vercel.app/api/results?team=8297",
					String.class
					);
			
			if(json != null && json.contains("FURIA")) {
				return "Último jogo: " + json.split("opponent")[1];
 			}
		}catch (Exception e) {
			logger.warn("Falha na API HLTV: {}" + e.getMessage());
		}
		
		return mockService.getLastMatch();
	}
	
	public String getPlayers() {
		return mockService.getPlayers();
	}

}
