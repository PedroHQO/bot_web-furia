package br.com.pedroqho.furiabot.model;


public class ChatMessage {
	private String sender;
	private String content;
	
	
	
	public ChatMessage(String sender, String content) {
		super();
		this.sender = sender;
		this.content = content;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
