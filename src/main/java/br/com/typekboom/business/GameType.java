package br.com.typekboom.business;

import br.com.typekboom.business.exception.GameTypeNotSuportedException;

public enum GameType {
	
	ENGLISH_TO_PORTUGUESE("english-to-portuguese"),
	PORTUGUESE_TO_ENGLISH("portuguese-to-english");
	
	private String stringType;

	private GameType(String stringType) {
		this.stringType = stringType;
	}
	
	public static GameType getType(String stringType) throws GameTypeNotSuportedException{
		for (GameType type : GameType.values()) {
			if(type.getStringType().equalsIgnoreCase(stringType)){
				return type;
			}
		}
		throw new GameTypeNotSuportedException();
	}

	public String getStringType() {
		return stringType;
	}

}