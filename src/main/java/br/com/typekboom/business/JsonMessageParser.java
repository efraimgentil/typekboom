package br.com.typekboom.business;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonParser;

import br.com.typekboom.business.exception.NotPossibleToCreateInstanceException;

public class JsonMessageParser implements MessageParser {

	@Override
	public <T> T translate(String message, Class<T> clazz) throws NotPossibleToCreateInstanceException {
		Map<String, String> jsonMap = processToMap(message);
		return mapToClassInstance(jsonMap, clazz);
	}
	
	protected <T> T mapToClassInstance(Map<String, String> jsonMap , Class<T> clazz) throws NotPossibleToCreateInstanceException{
		T instance = createInstance(clazz);
		
		Field declaredField = null;
		for ( String key : jsonMap.keySet() ) {
			try {
				declaredField = clazz.getDeclaredField( key);
				declaredField.setAccessible(true);
				try {
					declaredField.set( instance ,jsonMap.get(key) );
					declaredField.setAccessible(false);
					declaredField = null;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (NoSuchFieldException | SecurityException e) {
				// Just ignore if doesnot find the field				
				continue;
			}
		}
		
		return instance;
	}
	
	protected <T> T createInstance( Class<T> clazz ) throws NotPossibleToCreateInstanceException{
		try {
			return clazz.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new NotPossibleToCreateInstanceException();
		}
	}
	
	public Map<String, String> processToMap(String message) {
		Map<String, String> jsonMap = new HashMap<>();
		
		JsonParser parser = Json.createParser(new StringReader(message));
		String key = null;
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
				break;
			case KEY_NAME:
				key = parser.getString();
				jsonMap.put( key , null);
				break;
			case VALUE_STRING:
			case VALUE_NUMBER:
				try{
					if(key != null)
						jsonMap.put( key , parser.getString() );
				}finally{
					key = null;
				}
				break;
			}
		}
		return jsonMap;
	}
	
}
