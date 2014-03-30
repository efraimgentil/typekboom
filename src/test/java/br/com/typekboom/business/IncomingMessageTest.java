package br.com.typekboom.business;

import java.io.StringReader;
import java.lang.reflect.Field;

import javax.json.Json;
import javax.json.stream.JsonParser;

import org.junit.Test;

public class IncomingMessageTest {

	@Test
	public void t() {
		String message = "{ \"type\" : \"phrase\" , \"word\" :  \"hello\"   }";
		
		IncomingMessage translate = translate(message);
		
		
	}

	public IncomingMessage translate(String message) {
		IncomingMessage incomingMessage = new IncomingMessage();

		Class<?> clazz = IncomingMessage.class;
		Field declaredField = null;
		JsonParser parser = Json.createParser(new StringReader(message));
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			switch (event) {
			case START_ARRAY:
			case END_ARRAY:
			case START_OBJECT:
			case END_OBJECT:
			case VALUE_FALSE:
			case VALUE_NULL:
			case VALUE_TRUE:
				System.out.println(event.toString());
				break;
			case KEY_NAME:
				try {
					declaredField = clazz.getDeclaredField(parser.getString());
				} catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
				break;
			case VALUE_STRING:
			case VALUE_NUMBER:
				if (declaredField != null) {
					try {
						declaredField.setAccessible(true);
						declaredField.set(incomingMessage, parser.getString());
						declaredField.setAccessible(false);
						declaredField = null;
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}finally{
						declaredField = null;
					}
				}
				break;
			}
		}
		System.out.println( incomingMessage );
		return incomingMessage;
	}

}
