package br.com.typekboom.business.message;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.typekboom.business.MessageParser;
import br.com.typekboom.business.message.MessageIdentifier;
import br.com.typekboom.business.message.exception.MessageWithNoTypeException;
import br.com.typekboom.business.message.exception.WrongMessageTypeException;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MessageIdentifierTest {
	
	private MessageIdentifier identifier;
	private MessageParser parser;
	
	@Before
	public void setUp(){
		parser = mock(MessageParser.class);
		identifier = new MessageIdentifier( parser );
	}
	
	@Test
	public void givenAStringMessageWithTpeJoinGameShouldReturnAJoinGameSessionMessage() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "join-game");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		Message message  = identifier.discoverType(messageString);
		
		assertTrue("Should return a JoinGameSessionMessage" , message instanceof JoinGameSessionMessage );
	}
	
	@Test
	public void givenAStringMessageWithTypeUpdateGameShouldReturnAUpdateGameSessionMessage() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "update-game");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		Message message  = identifier.discoverType(messageString);
		
		assertTrue("Should return a UpdateGameSessionMessage" , message instanceof UpdateGameSessionMessage );
	}
	
	@Test
	public void givenAStringMessageWithTypeCloseGameShouldReturnACloseGameSessionMessage() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "close-game");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		Message message  = identifier.discoverType(messageString);
		
		assertTrue("Should return a UpdateGameSessionMessage" , message instanceof CloseGameSessionMessage );
	}
	
	@Test(expected = MessageWithNoTypeException.class)
	public void givenAStringMessageWithoutTypeShouldThrowMessageWithNoTypeException() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		Message message  = identifier.discoverType(messageString);
	}
	
	@Test(expected = WrongMessageTypeException.class)
	public void givenAStringMessageWithUnknowTypeShouldThrowWorngMessageTypeException() throws MessageWithNoTypeException, WrongMessageTypeException{
		String messageString = "MESSAGE IS NOT IMPORTANT FOR THE TEST";
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("type", "that don't even make sense");
		when( parser.processToMap(anyString()) ).thenReturn(  valuesMap );
		
		Message message  = identifier.discoverType(messageString);
	}
	
}
