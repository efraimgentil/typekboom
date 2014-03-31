package br.com.typekboom.business.message;

import java.util.Map;

import javax.websocket.Session;

import br.com.typekboom.business.JsonMessageParser;
import br.com.typekboom.business.MessageParser;
import br.com.typekboom.business.message.exception.MessageWithNoTypeException;
import br.com.typekboom.business.message.exception.WrongMessageTypeException;
import br.com.typekboom.business.message.handler.MessageHandler;

public class MessageHandlerIdentifier {
	
	private MessageParser parser;
	
	public MessageHandlerIdentifier(MessageParser parser) {
		this.parser = parser;  
	}
	
	public MessageHandler identifyHandler(String message) throws MessageWithNoTypeException, WrongMessageTypeException{
		Map<String, String> valuesMap = parser.processToMap(message);
		String type = valuesMap.get("type");
		if(type == null)
			throw new MessageWithNoTypeException();
		
		MessageHandler handler = MessageHandlerFactory.createMessage( MessageType.getType(type) );
		handler.setParser(parser);
		return handler;
	}
	
}
