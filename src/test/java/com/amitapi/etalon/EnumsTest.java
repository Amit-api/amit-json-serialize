package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.json.EnumsSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class EnumsTest extends TestBase {
	
	private static final Enums orig = new Enums().
			withE1( IntEnum.SECOND ).
			withE2Item( IntEnum.THIRD ).withE2Item( IntEnum.FIRST ).
			withE3( StringEnum.SECOND ).
			withE4( StringEnum.THIRD );
	
	private static final String json = 
			"{\"e1\":2,\"e2\":[3,1]," +
			"\"e3\":\"second\",\"e4\":\"th\\\"ird\"}";
	
	@Test
	public void testSerialize() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		EnumsSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals( json, writer.toString() );	
	}

	@Test
	public void testDeSerialize() throws IOException {
		JsonParser jp = parser(json);
		Enums obj = EnumsSerializer.readDynamic(jp);
		assertEquals( orig, obj );
		jp.close();
	}
}

