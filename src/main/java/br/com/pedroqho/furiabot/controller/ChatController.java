package br.com.pedroqho.furiabot.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.rometools.rome.feed.atom.Content;


import br.com.pedroqho.furiabot.model.ChatMessage;
import br.com.pedroqho.furiabot.model.service.FuriaNewsService;
import br.com.pedroqho.furiabot.model.service.HltvScraperService;
import br.com.pedroqho.furiabot.model.service.MockDataService;


@Controller
public class ChatController {
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	private FuriaNewsService newsService;
	
	@Autowired MockDataService mockService;
	
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public ChatMessage handleMEssage(ChatMessage message) {
		logger.info("Nova mensagem recebida de {}: {}", message.getSender(), message.getContent());
		if(message.getContent() == null || message.getContent().trim().isEmpty()) {
			return new ChatMessage("Bot FURIA", "Digite alfo para conversar ou /ajuda para ver os comandos");
		}
		
		String content = message.getContent().toLowerCase();
		
		try {
			return switch (content) {
				case "jogadores", "/jogadores" -> new ChatMessage("Bot FURIA", "👥 Elenco atual: " + newsService.getPlayers());
				case "próximo jogo", "proximo jogo" -> new ChatMessage("Bot FURIA", newsService.getNextMatchFromLiquipedia());
				case "último jogo", "ultimo jogo" -> new ChatMessage("Bot FURIA", newsService.getLastMatchFromHltvApi());
				case "noticias", "notícias" -> new ChatMessage("Bot FURIA", "📰 Notícias em breve...\n" + mockService.getLinks());
				case "sobre", "sobre-furia" -> new ChatMessage("Bot FURIA", mockService.getAbout());
				case "help", "ajuda", "/ajuda" -> new ChatMessage("Bot FURIA", getHelpMessage());
				default -> {
					if(content.startsWith("/")) {
						yield new ChatMessage("Bot FURIA", "Comando não reconhecido.\nDigite /ajuda para comandos");
					}else {
						yield new ChatMessage("Bot FURIA", "Olá! Sou o bot da FURIA, DIFITE /ajuda para ver o que posso fazer!");
					}
				}
			};
		}catch (Exception e) {
			logger.error("erro no chat: {}", e.getMessage());
			return new ChatMessage("Bot FURIA",  "⚠️ Erro temporário. Tente novamente!");
	}
}
	
	private String getHelpMessage() {
		return """
				💡 <b> Comandos Bot FURIA: </b> 💡
               /jogadores - Mostra o elenco
               /próximo jogo - Próximo jogo agendado
               /último jogo - Resultado do último jogo
               /notícias - Últimas notícias
               /sobre - Últimas estatísticas
               /ajuda - Mostra esta mensagem de ajuda
               
               💛🖤 #GoFURIA""";
	}
}
