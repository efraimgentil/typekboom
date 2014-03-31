package br.com.typekboom.business.message;

import java.util.Map;

import javax.websocket.Session;

import br.com.typekboom.business.JsonMessageParser;
import br.com.typekboom.business.MessageParser;
import br.com.typekboom.business.message.exception.MessageWithNoTypeException;
import br.com.typekboom.business.message.exception.WrongMessageTypeException;

public class MessageIdentifier {
	
	private MessageParser parser;
	
	public MessageIdentifier(MessageParser parser) {
		this.parser = parser;  
	}
	
	public void translateMessage(Session session , String message) throws MessageWithNoTypeException, WrongMessageTypeException{
		discoverType(message).handleMessage(session, message);
	}
	
	protected Message discoverType(String message) throws MessageWithNoTypeException, WrongMessageTypeException{
		Map<String, String> valuesMap = parser.processToMap(message);
		String type = valuesMap.get("type");
		if(type == null)
			throw new MessageWithNoTypeException();
		
		MessageType messageType = MessageType.getType(type);
		return MessageFactory.createMessage(messageType);
	}
	
}
