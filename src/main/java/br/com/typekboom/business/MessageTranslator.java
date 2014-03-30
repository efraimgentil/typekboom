package br.com.typekboom.business;

public interface MessageTranslator {
	
	public <T> T translate(String message , Class<T> clazz);
	
}
