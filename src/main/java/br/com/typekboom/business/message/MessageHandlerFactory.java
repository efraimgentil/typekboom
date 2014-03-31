package br.com.typekboom.business.message;

import br.com.typekboom.business.message.handler.CloseGameSessionHandler;
import br.com.typekboom.business.message.handler.JoinGameSessionHandler;
import br.com.typekboom.business.message.handler.MessageHandler;
import br.com.typekboom.business.message.handler.UpdateGameSessionHandler;

public class MessageHandlerFactory {

	public static MessageHandler createMessage(MessageType messageType) {
		switch (messageType) {
			case JOIN_GAME:
				return new JoinGameSessionHandler();
			case UPDATE_GAME:
				return new UpdateGameSessionHandler();
			case CLOSE_GAME:
				return new CloseGameSessionHandler();
		}
		return null;
	}

}
