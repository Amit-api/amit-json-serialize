package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import com.amitapi.TestBase;

public class MyArrayExceptionTest extends TestBase {
	private static LocalDateTime dateTime = LocalDateTime.of( 2001, 2, 6, 3, 10, 4 );
	private static UUID uuid = UUID.fromString( "f117adfb-6634-4ff9-bda6-dd1c8dca3380" );
	private static String json = 
			"{\"__type\":\"MyArrayException\",\"theBoolens\":[true,false],\"theInts\":[10,12],\"theLongs\":[100]," +
			"\"theDoubles\":[2.3],\"theStrings\":[\"Hello\"],\"theDates\":[\"2001-02-06T03:10:04\"]," +
			"\"theUiids\":[\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"]}";		
	private static MyArrayException orig = new MyArrayException().
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
	public void testSerializeJson() throws IOException {
		assertEquals( json, serialize( orig ) );
	}
	
	@Test
	public void testDeSerializeJson() throws IOException {
		MyArrayException obj = MyArrayException.__deserialize( parser( json ) );		
		assertEquals( orig, obj );
	}
}
