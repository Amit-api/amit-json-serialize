package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.json.ComplexTypeSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class ComplexTypeTest extends TestBase {
	private static final String json =  
			"{\"base\":" +
					"{\"__type\":\"Derived2\",\"v2\":2,\"v1\":1}," + 
				"\"moreBase\":" +
					"[{\"__type\":\"Derived1\",\"v2\":3,\"v1\":4}]}";

	private static final String typeMidJson =  
			"{\"base\":" +
					"{\"v2\":2,\"__type\":\"Derived2\",\"v1\":1}," + 
				"\"moreBase\":" +
					"[{\"v2\":3,\"__type\":\"Derived1\",\"v1\":4}]}";
	
	private static final String typeEndJson =  
			"{\"base\":" +
					"{\"v2\":2,\"v1\":1,\"__type\":\"Derived2\"}," + 
				"\"moreBase\":" +
					"[{\"v2\":3,\"v1\":4,\"__type\":\"Derived1\"}]}";

	private static final String typeNoType =  
			"{\"base\":" +
					"{\"v2\":2,\"v1\":1}," + 
				"\"moreBase\":" +
					"[{\"v2\":3,\"v1\":4}]}";
	
	private static final ComplexType orig = new ComplexType().
			withBase( new Derived2().withV2( 2 ).withV1( 1 )  ).
			withMoreBaseItem( new Derived1().withV2( 3 ).withV1( 4 ) );

	private static final ComplexType origNoType = new ComplexType().
			withBase( new Base().withV1( 1 )  ).
			withMoreBaseItem( new Base().withV1( 4 ) );
	@Test
	public void testSerialize() throws IOException {			
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		ComplexTypeSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals(json, writer.toString());
	}
	
	@Test
	public void testDeSerialize() throws IOException {
		JsonParser jp = parser(json);
		ComplexType obj = ComplexTypeSerializer.readDynamic(jp);
		jp.close();
		assertEquals(orig, obj);
	}

	@Test
	public void testDeSerializeTypeMidJson() throws IOException {
		JsonParser jp = parser(typeMidJson);
		ComplexType obj = ComplexTypeSerializer.readDynamic(jp);
		jp.close();
		assertEquals(orig, obj);
	}

	@Test
	public void testDeSerializeTypeEndJson() throws IOException {
		JsonParser jp = parser(typeEndJson);
		ComplexType obj = ComplexTypeSerializer.readDynamic(jp);
		jp.close();
		assertEquals(orig, obj);
	}

	@Test
	public void testDeSerializeNoType() throws IOException {
		JsonParser jp = parser(typeNoType);
		ComplexType obj = ComplexTypeSerializer.readDynamic(jp);
		jp.close();
		assertEquals(origNoType, obj);
	}
}
