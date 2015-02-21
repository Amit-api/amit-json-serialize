package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class EnumsTest {
	private static final JsonFactory jfactory = new JsonFactory();
	private static final Enums orig = new Enums().
			withE1( IntEnum.SECOND ).
			withE2( IntEnum.THIRD ).withE2( IntEnum.FIRST ).
			withE3( StringEnum.SECOND ).
			withE4( StringEnum.THIRD );
	
	private static final String json =  
			"{\"__type\":\"Enums\"," +
			"\"e1\":2,\"e2s\":[3,1]," +
			"\"e3\":\"second\",\"e4\":\"th\\\"ird\"}";
	
	@Test
	public void testSerialize() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator j = jfactory.createGenerator( writer );
		
		orig.serialize( j );
		j.close();
		
		assertEquals( json, writer.toString() );	
	}

	@Test
	public void testDeSerialize() throws IOException {
		JsonParser j = jfactory.createParser( json );
		
		Enums obj = Enums.deserialize( j );
		
		assertEquals( orig, obj );
	}	
	
}

