package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

public class MyExceptionTest extends TestBase {
	/*
	private static LocalDateTime dateTime = LocalDateTime.of( 2001, 2, 6, 3, 10, 4 );
	private static UUID uuid = UUID.fromString( "f117adfb-6634-4ff9-bda6-dd1c8dca3380" );
	private static String json = 
			"{\"__type\":\"MyException\",\"theBoolen\":true,\"theInt\":10,\"theLong\":100," +
			"\"theDouble\":2.3,\"theString\":\"Hello\",\"theDate\":\"2001-02-06T03:10:04\"," +
			"\"theUiid\":\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"}";
	private static String jsonWithMsg = 
			"{\"__type\":\"MyException\",\"__msg\":\"some\",\"theBoolen\":true,\"theInt\":10,\"theLong\":100," +
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

	private static MyException origWithMsg = new MyException( "some" ).
		withTheBoolen( true ).
		withTheInt( 10 ).
		withTheLong( 100L ).
		withTheDouble( 2.3 ).
		withTheString( "Hello" ).
		withTheDate( dateTime ).
		withTheUiid( uuid );
		
	@Test
	public void testSerializeJson() throws IOException {
		assertEquals( json, serialize( orig ) );
	}

	@Test
	public void testDeSerializeJson() throws IOException {
		MyException obj = MyException.__deserialize( parser( json ) );
		assertEquals( orig, obj );
	}
	
	@Test
	public void testSerializeJsonWithMsg() throws IOException {
		assertEquals( jsonWithMsg, serialize( origWithMsg ) );
	}
	
	@Test
	public void testDeSerializeJsonWithMsg() throws IOException {
		MyException obj = MyException.__deserialize( parser( jsonWithMsg ) );		
		assertEquals( origWithMsg, obj );
		assertEquals( "some", obj.getMessage() );
	}*/
}
