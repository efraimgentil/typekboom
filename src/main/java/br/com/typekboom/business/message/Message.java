package br.com.typekboom.business.message;

import javax.websocket.Session;

public interface Message {
	
	public void handleMessage(Session session, String message);
	
}
