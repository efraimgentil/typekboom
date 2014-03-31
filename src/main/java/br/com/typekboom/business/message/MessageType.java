package br.com.typekboom.business.message;

import br.com.typekboom.business.message.exception.WrongMessageTypeException;

public enum MessageType {
	
	JOIN_GAME("join-game"),
	UPDATE_GAME("update-game"),
	CLOSE_GAME("close-game");
	
	private String stringType;

	private MessageType(String stringType) {
		this.stringType = stringType;
	}
	
	public static MessageType getType(String type) throws WrongMessageTypeException{
		for (MessageType messageType : MessageType.values()) {
			if(messageType.stringType.equalsIgnoreCase(type)){
				return messageType;
			}
		}
		throw new WrongMessageTypeException();
	}
	
	
}
