package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import com.amitapi.TestBase;

public class PrimitiveTypeTest extends TestBase {
	private static LocalDateTime dateTime = LocalDateTime.of( 2001, 2, 6, 3, 10, 4 );
	private static UUID uuid = UUID.fromString( "f117adfb-6634-4ff9-bda6-dd1c8dca3380" );
	private static String json = 
			"{\"__type\":\"PrimitiveTypes\",\"theBoolen\":true,\"theInt\":10,\"theLong\":100," +
			"\"theDouble\":2.3,\"theString\":\"Hello\",\"theDate\":\"2001-02-06T03:10:04\"," +
			"\"theUiid\":\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"}";

	private static PrimitiveTypes orig = new PrimitiveTypes().
		withTheBoolen( true ).
		withTheInt( 10 ).
		withTheLong( 100L ).
		withTheDouble( 2.3 ).
		withTheString( "Hello" ).
		withTheDate( dateTime ).
		withTheUiid( uuid );
	
	@Test
	public void testPrimitiveSerializeJson() throws IOException {				
		assertEquals( json, serialize( orig ) );
	}

	@Test
	public void testPrimitiveDeSerializeJson() throws IOException {
		PrimitiveTypes obj = PrimitiveTypes.__deserialize( parser( json ) );		
		assertEquals( orig, obj );
	}
}
