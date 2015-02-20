package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class ComplexTypeTest {
	private static JsonFactory jfactory = new JsonFactory();

	@Test
	public void testSerialize() throws IOException {
		ComplexType obj = new ComplexType().
				withBase( new Derived2().withV2( 2 ).withV1( 1 )  ).
				withMoreBase( new Derived1().withV2( 3 ).withV1( 4 ) );
		
		StringWriter writer = new StringWriter();
		JsonGenerator j = jfactory.createGenerator( writer );
		obj.serialize( j );
		j.close();
		
		assertEquals(
			"{\"__type\":\"ComplexType\",\"base\":" +
					"{\"__type\":\"Derived2\",\"v1\":1,\"v2\":2}," + 
				"\"moreBases\":" +
					"[{\"__type\":\"Derived1\",\"v1\":4,\"v2\":3}]}",
			writer.toString() );
		
	}
}
