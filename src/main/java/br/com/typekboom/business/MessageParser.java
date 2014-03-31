package br.com.typekboom.business;

import java.util.Map;

public interface MessageParser {
	
	public <T> T translate(String message , Class<T> clazz);
	
	public Map<String, String> processToMap(String message);
	
}
