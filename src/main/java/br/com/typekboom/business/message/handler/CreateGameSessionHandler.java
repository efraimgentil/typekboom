package br.com.typekboom.business.message.handler;

import java.io.IOException;

import javax.websocket.Session;

import br.com.typekboom.business.GameSessionManager;
import br.com.typekboom.business.GameType;
import br.com.typekboom.business.exception.GameTypeNotSuportedException;
import br.com.typekboom.business.message.CreateGameSessionMessage;

public class CreateGameSessionHandler extends MessageHandler {
	
	
	@Override
	public void handleMessage(Session session, String messageAsString) {
		CreateGameSessionMessage message = parser.translate( messageAsString, CreateGameSessionMessage.class );
		try{
			createGame( GameSessionManager.getInstance() , session , message );
		}catch(GameTypeNotSuportedException nse){
			try {
				//TODO
				session.getBasicRemote().sendText("SHOULD BE SOMETHING USEFUL");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void createGame( GameSessionManager manager , Session session , CreateGameSessionMessage message ) throws GameTypeNotSuportedException{
		GameType gameType = GameType.getType( message.getGameType() );
		manager.createGameSession( session , message.getSessionName() , gameType);
	}

}
