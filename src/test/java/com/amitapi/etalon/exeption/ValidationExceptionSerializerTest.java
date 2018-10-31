package com.amitapi.etalon.exeption;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.TestBase;
import com.amitapi.etalon.exception.ValidationException;
import com.amitapi.etalon.json.exception.ValidationExceptionSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class ValidationExceptionSerializerTest extends TestBase {

	private static final ValidationException emptyObject = new ValidationException();
	private static final String emptyObjectString = "{\"__type\":\"exception.validation\"}";

	private static final ValidationException someObject = new ValidationException().withReason("something is wrong").withCode("tada");
	private static final String someObjectString = "{\"__type\":\"exception.validation\",\"code\":\"tada\",\"reason\":\"something is wrong\"}";
	private static final String someObjectStringWithNoise = "{\"v1\":\"p1\",\"code\":\"tada\",\"__type\":\"exception.validation\",\"v2\":\"p2\",\"reason\":\"something is wrong\",\"v3\":null}";
	
	@Test
	public void testSerializeEmptyObject() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			ValidationExceptionSerializer.WRITER.write(jg, emptyObject);	
		}
		assertEquals(emptyObjectString, writer.toString());
	}

	@Test
	public void testDeSerializeEmptyObject() throws IOException {
		try (JsonParser jp = parser(emptyObjectString)) {
			jp.nextToken();
			ValidationException obj = ValidationExceptionSerializer.READER
					.read(jp);

			assertEquals(emptyObject.getReason(), obj.getReason());
			assertEquals(emptyObject.getCode(), obj.getCode());
		}
	}
	
	@Test
	public void testSerializeSomeObject() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			ValidationExceptionSerializer.WRITER.write(jg, someObject);	
		}
		assertEquals(someObjectString, writer.toString());
	}

	@Test
	public void testDeSerializeSomeObject() throws IOException {
		try (JsonParser jp = parser(someObjectString)) {
			jp.nextToken();
			ValidationException obj = ValidationExceptionSerializer.READER
					.read(jp);

			assertEquals(someObject.getReason(), obj.getReason());
			assertEquals(someObject.getCode(), obj.getCode());
		}
	}

	@Test
	public void testDeSerializeSomeObjectWithNoise() throws IOException {
		try (JsonParser jp = parser(someObjectStringWithNoise)) {
			jp.nextToken();
			ValidationException obj = ValidationExceptionSerializer.READER
					.read(jp);

			assertEquals(someObject.getReason(), obj.getReason());
			assertEquals(someObject.getCode(), obj.getCode());
		}
	}
}
