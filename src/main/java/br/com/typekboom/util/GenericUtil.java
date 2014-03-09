package br.com.typekboom.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtil {
	
	public <T> Class<T> getGenericClassType(Class<?> clazz , int genericDeclarationPosition ){
		Type type = clazz.getGenericSuperclass();

		ParameterizedType paramType;
		try {
			paramType = (ParameterizedType) type;
		} catch (ClassCastException cause) {
			paramType = (ParameterizedType) ((Class<T>) type)
					.getGenericSuperclass();
		}

		return (Class<T>) paramType.getActualTypeArguments()[genericDeclarationPosition];
	}
	
}
