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

public class ArrayPrimitiveTypesTest {
	private static LocalDateTime dateTime = LocalDateTime.of( 2001, 2, 6, 3, 10, 4 );
	private static UUID uuid = UUID.fromString( "f117adfb-6634-4ff9-bda6-dd1c8dca3380" );
	private static JsonFactory jfactory = new JsonFactory();
	private static String json = 
			"{\"__type\":\"ArrayPrimitiveTypes\",\"theBoolens\":[true,false],\"theInts\":[10,12],\"theLongs\":[100]," +
			"\"theDoubles\":[2.3],\"theStrings\":[\"Hello\"],\"theDates\":[\"2001-02-06T03:10:04\"]," +
			"\"theUiids\":[\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"]}";		
	private static ArrayPrimitiveTypes orig = new ArrayPrimitiveTypes().
			withTheBoolen( true ).
			withTheBoolen( false ).
			withTheInt( 10 ).
			withTheInt( 12 ).
			withTheLong( 100L ).
			withTheDouble( 2.3 ).
			withTheString( "Hello" ).
			withTheDate( dateTime ).
			withTheUiid( uuid );

	@Test
	public void testArrayPrimitiveSerializeJson() throws IOException {
		
		StringWriter writer = new StringWriter();
		JsonGenerator j = jfactory.createGenerator( writer );
		orig.serialize( j );
		j.close();
		
		assertEquals( json, writer.toString() );
	}
	
	@Test
	public void testArrayPrimitiveDeSerializeJson() throws IOException {
		JsonParser j = jfactory.createParser( json );
		
		ArrayPrimitiveTypes obj = ArrayPrimitiveTypes.deserialize( j );
		
		assertEquals( orig, obj );
	}
}