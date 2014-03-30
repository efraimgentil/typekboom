package br.com.typekboom.business.message;

import javax.websocket.Session;

public class MessageTranslator {
	
	public void translateMessage(Session session , String message){
		discoverType(message).handleMessage(session, message);
	}
	
	protected Message discoverType(String message){
		return new JoinGameSessionMessage();
	}
	
	
}
