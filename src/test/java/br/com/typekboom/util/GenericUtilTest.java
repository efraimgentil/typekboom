package br.com.typekboom.util;

import org.junit.Before;
import org.junit.Test;

import br.com.typekboom.persistence.DefaultDao;

import static org.junit.Assert.*;

public class GenericUtilTest {
	
	private GenericUtil genericUtil;
	private class GenericTest extends DefaultDao<String, Integer>{}
	
	@Before
	public void setup(){
		genericUtil = new GenericUtil();
	}
	
	@Test
	public void shouldReturnTheGenericTypeClassInTheGivenPosition(){
		Class<?> genericTypeClass = genericUtil.getGenericClassType( GenericTest.class , 0 );
		
		assertEquals( String.class , genericTypeClass );
	}
	
}