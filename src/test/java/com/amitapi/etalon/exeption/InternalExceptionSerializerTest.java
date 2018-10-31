package com.amitapi.etalon.exeption;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.TestBase;
import com.amitapi.etalon.exception.InternalException;
import com.amitapi.etalon.json.exception.InternalExceptionSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class InternalExceptionSerializerTest extends TestBase {

	private static final InternalException emptyObject = new InternalException();
	private static final String emptyObjectString = "{\"__type\":\"exception.internal\"}";

	private static final InternalException someObject = new InternalException().withReason("something is wrong");
	private static final String someObjectString = "{\"__type\":\"exception.internal\",\"reason\":\"something is wrong\"}";
	private static final String someObjectStringWithNoise = "{\"v1\":\"p1\",\"__type\":\"exception.internal\",\"v2\":\"p2\",\"reason\":\"something is wrong\",\"v3\":null}";
	
	@Test
	public void testSerializeEmptyObject() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			InternalExceptionSerializer.WRITER.write(jg, emptyObject);	
		}
		assertEquals(emptyObjectString, writer.toString());
	}

	@Test
	public void testDeSerializeEmptyObject() throws IOException {
		try (JsonParser jp = parser(emptyObjectString)) {
			jp.nextToken();
			InternalException obj = InternalExceptionSerializer.READER
					.read(jp);

			assertEquals(emptyObject.getReason(), obj.getReason());
		}
	}
	
	@Test
	public void testSerializeSomeObject() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			InternalExceptionSerializer.WRITER.write(jg, someObject);	
		}
		assertEquals(someObjectString, writer.toString());
	}

	@Test
	public void testDeSerializeSomeObject() throws IOException {
		try (JsonParser jp = parser(someObjectString)) {
			jp.nextToken();
			InternalException obj = InternalExceptionSerializer.READER
					.read(jp);

			assertEquals(someObject.getReason(), obj.getReason());
		}
	}

	@Test
	public void testDeSerializeSomeObjectWithNoise() throws IOException {
		try (JsonParser jp = parser(someObjectStringWithNoise)) {
			jp.nextToken();
			InternalException obj = InternalExceptionSerializer.READER
					.read(jp);

			assertEquals(someObject.getReason(), obj.getReason());
		}
	}
}
