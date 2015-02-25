package com.amitapi.etalon.call;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class DoNothingResponseTest {
	private static JsonFactory jfactory = new JsonFactory();

	@Test
	public void testDeserializeOk() throws JsonParseException, IOException {
		JsonParser j = jfactory.createParser( "      " );
		
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize( j );
		
		assertNull( resp.getException() );
	}

	@Test
	public void testDeserializeOk2() throws JsonParseException, IOException {
		JsonParser j = jfactory.createParser( "" );
		
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize( j );
		
		assertNull( resp.getException() );
	}
	
	@Test( expected = IOException.class )
	public void testDeserializeOk3() throws JsonParseException, IOException {
		JsonParser j = jfactory.createParser( "jfksldk" );
		
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize( j );
		
		assertNull( resp.getException() );
	}
	
}
