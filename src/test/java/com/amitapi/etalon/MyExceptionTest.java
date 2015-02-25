package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class MyExceptionTest {
	
	private static LocalDateTime dateTime = LocalDateTime.of( 2001, 2, 6, 3, 10, 4 );
	private static UUID uuid = UUID.fromString( "f117adfb-6634-4ff9-bda6-dd1c8dca3380" );
	private static JsonFactory jfactory = new JsonFactory();
	private static String json = 
			"{\"__type\":\"MyException\",\"theBoolen\":true,\"theInt\":10,\"theLong\":100," +
			"\"theDouble\":2.3,\"theString\":\"Hello\",\"theDate\":\"2001-02-06T03:10:04\"," +
			"\"theUiid\":\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"}";

	private static MyException orig = new MyException().
		withTheBoolen( true ).
		withTheInt( 10 ).
		withTheLong( 100L ).
		withTheDouble( 2.3 ).
		withTheString( "Hello" ).
		withTheDate( dateTime ).
		withTheUiid( uuid );
	
	@Test
	public void testSerializeJson() throws IOException {
				
		StringWriter writer = new StringWriter();
		JsonGenerator j = jfactory.createGenerator( writer );
		orig.__serialize( j );
		j.close();
		
		assertEquals( json, writer.toString() );
	}

	@Test
	public void testDeSerializeJson() throws IOException {
		JsonParser j = jfactory.createParser( json );
		
		MyException obj = MyException.__deserialize( j );
		
		assertEquals( orig, obj );
	}
}
