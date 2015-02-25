package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class ComplexTypeTest {
	private static final JsonFactory jfactory = new JsonFactory();
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
		StringWriter writer = new StringWriter();
		JsonGenerator j = jfactory.createGenerator( writer );
		orig.__serialize( j );
		j.close();
		
		assertEquals( json, writer.toString() );
	}
	
	@Test
	public void testDeSerialize() throws IOException {
		JsonParser j = jfactory.createParser( json );
		
		ComplexType obj = ComplexType.__deserialize( j );
		
		assertEquals( orig, obj );
	}	
}
