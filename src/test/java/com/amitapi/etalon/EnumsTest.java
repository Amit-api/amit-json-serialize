package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class EnumsTest extends TestBase {
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
		assertEquals( json, serialize( orig ) );	
	}

	@Test
	public void testDeSerialize() throws IOException {
		Enums obj = Enums.__deserialize( parser( json ) );
		assertEquals( orig, obj );
	}	
	
}

