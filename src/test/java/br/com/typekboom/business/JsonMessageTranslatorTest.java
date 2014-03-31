package br.com.typekboom.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.json.stream.JsonParsingException;

import org.junit.Before;
import org.junit.Test;

public class JsonMessageTranslatorTest {
	
	MessageParser translator;
	
	@Before
	public void setUp(){
		translator = new JsonMessageParser();
	}
	
	@Test
	public void givenAJsonStringShouldReturnObjectWithTheValuesInTheString(){
		String message = "{ \"type\" : \"phrase\" , \"word\" :  \"hello\"   }";
		
		IncomingMessage messageTranslated = translator.translate(message, IncomingMessage.class);
		
		assertNotNull("messageTranslated should not be null" , messageTranslated);
		assertEquals("The type value should be the same" , "phrase", messageTranslated.getType() );
		assertEquals("The word value should be the same" , "hello", messageTranslated.getWord() );
	}
	
	@Test
	public void givenAJsonStringWithMoreFieldThanTheActualClassShouldReturnObjectWithTheValuesInTheStringAndIgnoreTheOtherFields(){
		String message = "{ \"type\" : \"phrase\" , \"word\" :  \"hello\"  , \"noField\" : \"lol\"  }";
		
		IncomingMessage messageTranslated = translator.translate(message, IncomingMessage.class);
		
		assertNotNull("messageTranslated should not be null" , messageTranslated);
		assertEquals("The type value should be the same" , "phrase", messageTranslated.getType() );
		assertEquals("The word value should be the same" , "hello", messageTranslated.getWord() );
	}
	
	@Test(expected = JsonParsingException.class)
	public void givenAJsonStringWorngFormatedShouldThrowsJsonParsingException(){
		String message = "{ \"type\" : \"phrase\"  \"word\" :  \"hello\"  , \"noField\" : \"lol\"  }";
		
		IncomingMessage messageTranslated = translator.translate(message, IncomingMessage.class);
		
	}
	
}
