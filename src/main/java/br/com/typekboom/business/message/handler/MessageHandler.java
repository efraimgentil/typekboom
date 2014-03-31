package br.com.typekboom.business.message.handler;

import javax.websocket.Session;

import br.com.typekboom.business.MessageParser;

public abstract class MessageHandler {
	
	protected MessageParser parser;
	
	public abstract void handleMessage(Session session, String messageAsString);

	public void setParser(MessageParser parser) {
		this.parser = parser;
	}
	
	public MessageParser getParser(){
		return parser;
	}
	
}
