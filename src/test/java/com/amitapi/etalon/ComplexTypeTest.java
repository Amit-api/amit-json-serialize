package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ComplexTypeTest extends TestBase {
	private static final String json =  
			"{\"__type\":\"ComplexType\",\"base\":" +
					"{\"__type\":\"Derived2\",\"v1\":1,\"v2\":2}," + 
				"\"moreBases\":" +
					"[{\"__type\":\"Derived1\",\"v1\":4,\"v2\":3}]}";
	private static final ComplexType orig = new ComplexType().
			withBase( new Derived2().withV2( 2 ).withV1( 1 )  ).
			withMoreBase( new Derived1().withV2( 3 ).withV1( 4 ) );
	@Test
	public void testSerialize() throws IOException {			
		assertEquals( json, serialize( orig ) );
	}
	
	@Test
	public void testDeSerialize() throws IOException {
		ComplexType obj = ComplexType.__deserialize( parser( json ) );	
		assertEquals( orig, obj );
	}	
}
