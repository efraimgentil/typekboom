package br.com.typekboom.business;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import br.com.typekboom.business.exception.GameSessionNotFound;

public class GameSessionManager {
	
	private Map<String, GameSession> gameSessions;
	
	private static GameSessionManager manager = new GameSessionManager();
	
	private GameSessionManager() {
		gameSessions = new HashMap<>();
	}
	
	public static GameSessionManager getInstance(){
		return manager;
	}
	
	protected static void clearManager(){
		manager = new GameSessionManager();
	}
	
	public GameSession createGameSession(Session session, String sessionName ,  GameType gameType){
		String gameSessionId = String.valueOf( new Date().getTime() );
		GameSession gameSession = new GameSession( gameSessionId,  gameType );
		gameSession.addPlayer(session);
		gameSession.setSessionName(sessionName);
		session.getUserProperties().put("gameSession", gameSessionId );
		gameSessions.put( gameSessionId , gameSession );
		return gameSession;
	}
	
	public void destroyGameSession(String gameSessionId ){
		GameSession gameSession = gameSessions.get(gameSessionId);
		if(gameSession != null){
			if(!gameSession.hasPlayers()){
				gameSessions.remove(gameSessionId);
			}
		}
	}
	
	public GameSession getGameSession(String gameSessionId) throws GameSessionNotFound{
		GameSession gameSession = gameSessions.get(gameSessionId);
		if(gameSession == null){
			throw new GameSessionNotFound();
		}
		return gameSession;
	}
	
	public Collection<GameSession> getGameSessions(){
		return gameSessions.values();
	} 
	
	
	
}
