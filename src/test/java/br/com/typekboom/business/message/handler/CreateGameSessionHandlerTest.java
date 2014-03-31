package br.com.typekboom.business.message.handler;

import java.io.IOException;

import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import org.junit.Before;
import org.junit.Test;

import br.com.typekboom.business.GameSessionManager;
import br.com.typekboom.business.GameType;
import br.com.typekboom.business.MessageParser;
import br.com.typekboom.business.exception.GameTypeNotSuportedException;
import br.com.typekboom.business.message.CreateGameSessionMessage;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CreateGameSessionHandlerTest {
	
	private CreateGameSessionHandler handler;
	private GameSessionManager manager;
	private Session session;
	private MessageParser parser;
	
	@Before
	public void setUp(){
		manager = mock(GameSessionManager.class);
		session = mock(Session.class);
		parser = mock(MessageParser.class);
		handler = new CreateGameSessionHandler();
		handler.setParser(parser);
	}
	
	@Test(expected = GameTypeNotSuportedException.class)
	public void givenANotSuportedGameTypeShouldThrowGameTypeNotSuportedException(){
		CreateGameSessionMessage message = mock(CreateGameSessionMessage.class);
		
		handler.createGame( manager, session, message );
		
	}
	
	@Test
	public void givenAValidCreateGameSessionMessageShouldCreateAGameSession(){
		CreateGameSessionMessage message = mock(CreateGameSessionMessage.class);
		GameType expectedGameType = GameType.ENGLISH_TO_PORTUGUESE ;
		String expectedSessionName = "MY SESSION NAME";
		when( message.getGameType() ).thenReturn( GameType.ENGLISH_TO_PORTUGUESE.getStringType() );
		when( message.getSessionName() ).thenReturn(expectedSessionName);
		
		handler.createGame( manager, session, message );
		
		verify( manager , times(1) ).createGameSession(session, expectedSessionName , expectedGameType );
	}
	
	@Test
	public void givenAValidStringMEssageShouldCreateAGameSessionWithoutErrors(){
		String messageAsString = "{ \"gameType\" : \"portuguese-to-english\" , \"sessionName\" : \"MY SESSION GAME\" }";
		CreateGameSessionMessage message = new CreateGameSessionMessage();
		message.setGameType("portuguese-to-english");
		message.setSessionName("MY SESSION GAME");
		when( parser.translate( messageAsString , CreateGameSessionMessage.class  ) ).thenReturn( message );
		
		handler.handleMessage(session, messageAsString);
		
		verify( parser , times(1) ).translate( messageAsString , CreateGameSessionMessage.class );
	}
	
	@Test
	public  void givenAInvalidGameTypeShouldSendAMessageBackToTheUser() throws IOException{
		String messageAsString = "DONT REALLY METHER";
		CreateGameSessionMessage message = new CreateGameSessionMessage();
		message.setGameType("UNKOWN GAME TYPE");
		Basic basicRemote = mock(Basic.class);
		when( parser.translate( messageAsString , CreateGameSessionMessage.class  ) ).thenReturn( message );
		when( session.getBasicRemote() ).thenReturn(basicRemote);
		
		handler.handleMessage(session, messageAsString);
		
		verify( parser , times(1) ).translate( messageAsString , CreateGameSessionMessage.class );
		verify( session , times(1) ).getBasicRemote();
		verify( basicRemote , times(1) ).sendText( anyString() );
	}
	
	
	
}
