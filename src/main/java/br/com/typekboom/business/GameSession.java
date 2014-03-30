package br.com.typekboom.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import br.com.typekboom.model.TranslationObjective;

public class GameSession {
	
	private String gameSessionId;
	private String sessionName;
	private Map<String, Session> players;
	private GameType gameType;
	private List<TranslationObjective> objectives;
	
	public GameSession(String gameSessionId , GameType gameType) {
		players = new HashMap<String, Session>();
		this.gameType = gameType;
		this.gameSessionId = gameSessionId;
	}
	
	public void	receiveMessage( Session session , String message ){
		
	}
	
	public void addPlayer(Session session){
		players.put( session.getId() , session );
	}
	
	public boolean hasPlayers(){
		return players.isEmpty();
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getGameSessionId() {
		return gameSessionId;
	}
	
}