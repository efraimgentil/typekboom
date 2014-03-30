package br.com.typekboom.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.typekboom.business.exception.GameSessionNotFound;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class GameSessionManagerTest {
	
	private GameSessionManager manager;
	
	@Before
	public void setUp(){
		manager = GameSessionManager.getInstance();
	}
	
	@After
	public void destroy(){
		GameSessionManager.clearManager();
	}
	
	@Test
	public void givenASessionAndAGameTypeShouldCreateANewGameSession(){
		Map<String, Object> mapParameter = new HashMap<>();
		Session session = mock(Session.class);
		when(session.getUserProperties() ).thenReturn(mapParameter);
		Integer oldSize = manager.getGameSessions().size();
		
		manager.createGameSession(session, GameType.ENGLISH_TO_PORTUGUESE );
		
		assertNotSame("Should have created a new gameSession" ,  oldSize , manager.getGameSessions().size() );
	}
	
	@Test
	public void givenASessionAndAGameTypeShouldCreateANewGameSessionAndSetTheGameSessionIdInTheSessionParameters(){
		Map<String, Object> mapParameter = new HashMap<>();
		Session session = mock(Session.class);
		when(session.getUserProperties() ).thenReturn(mapParameter);
		
		manager.createGameSession(session, GameType.ENGLISH_TO_PORTUGUESE );
		
		assertNotNull( "Should have set the gameSession parameter" , mapParameter.get("gameSession") );
	}
	
	@Test
	public void givenAGameSessionIdIfExistShouldRemoveFromTheGameSessionMap(){
		Map<String, Object> mapParameter = new HashMap<>();
		Session session = mock(Session.class);
		when(session.getUserProperties() ).thenReturn(mapParameter);
		manager.createGameSession(session, GameType.ENGLISH_TO_PORTUGUESE );
		String gameSessionId = (String) mapParameter.get("gameSession");
		
		assertNotSame("Should have 1 open session" , 0 , manager.getGameSessions().size() );
		
		manager.destroyGameSession( gameSessionId );
		
		assertSame("Should have closed the open session" ,  0 , manager.getGameSessions().size() );
	}
	
	@Test
	public void givenAGameSessionIdDoesNotExistShouldDoNothing(){
		Map<String, Object> mapParameter = new HashMap<>();
		Session session = mock(Session.class);
		when(session.getUserProperties() ).thenReturn(mapParameter);
		manager.createGameSession(session, GameType.ENGLISH_TO_PORTUGUESE );
		String gameSessionId = "";
		
		manager.destroyGameSession( gameSessionId );
		
		assertNotSame("Should have 1 open session" , 0 , manager.getGameSessions().size() );
	}
	
	@Test
	public void givenAGameSessionIdShouldReturnTheGameSession() throws GameSessionNotFound{
		Map<String, Object> mapParameter = new HashMap<>();
		Session session = mock(Session.class);
		when(session.getUserProperties() ).thenReturn(mapParameter);
		manager.createGameSession(session, GameType.ENGLISH_TO_PORTUGUESE );
		String gameSessionId = (String) mapParameter.get("gameSession");
		
		GameSession gameSession = manager.getGameSession(gameSessionId);
		
		assertNotNull("Should have returned the gameSession" , gameSession );
		assertEquals("The gameSession should have the same id" , gameSessionId , gameSession.getGameSessionId());
	}
	
	@Test(expected = GameSessionNotFound.class)
	public void givenAWrongGameSessionIdShouldThrowException() throws GameSessionNotFound{
		String gameSessionId = "bleh";
		
		GameSession gameSession = manager.getGameSession(gameSessionId);
	}
	
	
}
