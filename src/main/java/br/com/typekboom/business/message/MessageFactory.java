package br.com.typekboom.business.message;

public class MessageFactory {

	public static Message createMessage(MessageType messageType) {
		switch (messageType) {
			case JOIN_GAME:
				return new JoinGameSessionMessage();
			case UPDATE_GAME:
				return new UpdateGameSessionMessage();
			case CLOSE_GAME:
				return new CloseGameSessionMessage();
		}
		return null;
	}

}
