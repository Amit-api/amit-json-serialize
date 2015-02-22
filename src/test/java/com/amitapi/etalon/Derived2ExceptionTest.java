package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class Derived2ExceptionTest {

	private static final JsonFactory jfactory = new JsonFactory();
	private static final String json =  
			"{\"__type\":\"Derived2Exception\",\"v1\":1,\"v2\":2}";
	private static final BaseException orig = new Derived2Exception().
			withV2( 2 ).withV1( 1 );
	
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
		
		BaseException obj = BaseException.deserialize( j );
		
		assertEquals( orig, obj );
	}	

}
