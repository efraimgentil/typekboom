package br.com.typekboom.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{name}")
public class ChatWS {
	@OnOpen
	public void open(@PathParam("name") String name, Session session){
		session.getUserProperties().put("name", name );
	}
	@OnMessage
	public void message(String message , Session userSession) throws IOException{
		for( Session session : userSession.getOpenSessions() ) {
			session.getBasicRemote().sendText( message );
		}
	}
	@OnClose
	public void close(Session userSession) throws IOException{
		String name = (String) userSession.getUserProperties().get("name");
		for( Session session : userSession.getOpenSessions() ) {
			session.getBasicRemote().sendText( name + ": saiu!" );
		}
	}
}