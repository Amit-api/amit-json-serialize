package com.amitapi.etalon.exeption;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.TestBase;
import com.amitapi.etalon.exception.AccessExpiredException;
import com.amitapi.etalon.json.exception.AccessExpiredExceptionSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class AccessExpiredExceptionSerializerTest extends TestBase {

	private static final AccessExpiredException emptyObject = new AccessExpiredException();
	private static final String emptyObjectString = "{\"__type\":\"exception.expired\"}";

	private static final AccessExpiredException someObject = new AccessExpiredException().withReason("something is wrong");
	private static final String someObjectString = "{\"__type\":\"exception.expired\",\"reason\":\"something is wrong\"}";
	private static final String someObjectStringWithNoise = "{\"v1\":\"p1\",\"__type\":\"exception.expired\",\"v2\":\"p2\",\"reason\":\"something is wrong\",\"v3\":null}";
	
	@Test
	public void testSerializeEmptyObject() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			AccessExpiredExceptionSerializer.WRITER.write(jg, emptyObject);	
		}
		assertEquals(emptyObjectString, writer.toString());
	}

	@Test
	public void testDeSerializeEmptyObject() throws IOException {
		try (JsonParser jp = parser(emptyObjectString)) {
			jp.nextToken();
			AccessExpiredException obj = AccessExpiredExceptionSerializer.READER
					.read(jp);

			assertEquals(emptyObject.getReason(), obj.getReason());
		}
	}
	
	@Test
	public void testSerializeSomeObject() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			AccessExpiredExceptionSerializer.WRITER.write(jg, someObject);	
		}
		assertEquals(someObjectString, writer.toString());
	}

	@Test
	public void testDeSerializeSomeObject() throws IOException {
		try (JsonParser jp = parser(someObjectString)) {
			jp.nextToken();
			AccessExpiredException obj = AccessExpiredExceptionSerializer.READER
					.read(jp);

			assertEquals(someObject.getReason(), obj.getReason());
		}
	}

	@Test
	public void testDeSerializeSomeObjectWithNoise() throws IOException {
		try (JsonParser jp = parser(someObjectStringWithNoise)) {
			jp.nextToken();
			AccessExpiredException obj = AccessExpiredExceptionSerializer.READER
					.read(jp);

			assertEquals(someObject.getReason(), obj.getReason());
		}
	}
}
