package br.com.typekboom.business.message;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.typekboom.business.MessageParser;
import br.com.typekboom.business.message.MessageHandlerIdentifier;
import br.com.typekboom.business.message.exception.MessageWithNoTypeException;
import br.com.typekboom.business.message.exception.WrongMessageTypeException;
import br.com.typekboom.business.message.handler.CloseGameSessionHandler;
import br.com.typekboom.business.message.handler.JoinGameSessionHandler;
import br.com.typekboom.business.message.handler.MessageHandler;
import br.com.typekboom.business.message.handler.UpdateGameSessionHandler;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MessageHandlerIdentifierTest {
	
	private MessageHandlerIdentifier identifier;
	private MessageParser parser;
	
	@Before
	public void setUp(){
		parser = mock(MessageParser.class);
		identifier = new MessageHandlerIdentifier( parser );
	}
	
	@Test
	public void givenAStringMessageWithTpeJoinGameShouldReturnAJoinGameSessionMessage() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "join-game");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		MessageHandler message  = identifier.identifyHandler(messageString);
		
		assertTrue("Should return a JoinGameSessionHandler" , message instanceof JoinGameSessionHandler );
	}
	
	@Test
	public void givenAStringMessageWithTypeUpdateGameShouldReturnAUpdateGameSessionMessage() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "update-game");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		MessageHandler message  = identifier.identifyHandler(messageString);
		
		assertTrue("Should return a UpdateGameSessionHandler" , message instanceof UpdateGameSessionHandler );
	}
	
	@Test
	public void givenAStringMessageWithTypeCloseGameShouldReturnACloseGameSessionMessage() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "close-game");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		MessageHandler message  = identifier.identifyHandler(messageString);
		
		assertTrue("Should return a CloseGameSessionHandler" , message instanceof CloseGameSessionHandler );
	}
	
	@Test
	public void givenAStringMessageWithTpeJoinGameShouldReturnAJoinGameSessionMessageAndSetTheMessageParser() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "join-game");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		MessageHandler message  = identifier.identifyHandler(messageString);
		
		assertTrue("Should return a JoinGameSessionHandler" , message instanceof JoinGameSessionHandler );
		assertEquals("Should have the same parser" ,  parser , message.getParser() );
	}
	
	@Test(expected = MessageWithNoTypeException.class)
	public void givenAStringMessageWithoutTypeShouldThrowMessageWithNoTypeException() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		MessageHandler message  = identifier.identifyHandler(messageString);
	}
	
	@Test(expected = WrongMessageTypeException.class)
	public void givenAStringMessageWithUnknowTypeShouldThrowWorngMessageTypeException() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "that don't even make sense");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		MessageHandler message  = identifier.identifyHandler(messageString);
	}
	
}
