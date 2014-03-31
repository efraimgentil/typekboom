package br.com.typekboom.business.message;

/*
 * @author efraimgentil (efraim.gentil@gmail.com)
 */
public class CreateGameSessionMessage {

	private String sessionName;
	private String gameType;
	
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
}