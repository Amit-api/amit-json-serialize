package com.amitapi.etalon.call;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.TestBase;
import com.amitapi.etalon.exception.AccessDeniedException;
import com.amitapi.etalon.exception.AccessExpiredException;
import com.amitapi.etalon.exception.InternalException;
import com.amitapi.etalon.exception.ValidationException;
import com.amitapi.etalon.json.call.CallAllInt;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class DoNothingResponseTest extends TestBase {

	@Test
	public void test_DS_Empty() throws JsonParseException, IOException {
		try (JsonParser jp = parser("      ")) {
			CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse
					.readDynamic(jp);
			resp.throwException();
		}
	}

	@Test
	public void test_SZ_Empty() throws JsonParseException, IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallAllInt.DoNothingResponse.writeDynamic(jg,
					new CallAllInt.DoNothingResponse());
		}

		assertEquals("", writer.toString());
	}

	@Test
	public void test_DS_EmptyMin() throws JsonParseException, IOException {
		try (JsonParser jp = parser("")) {
			CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse
					.readDynamic(jp);
			resp.throwException();
		}
	}

	@Test(expected = JsonParseException.class)
	public void test_DS_InvalidImput() throws JsonParseException, IOException {
		try (JsonParser jp = parser("hello")) {
			CallAllInt.DoNothingResponse.readDynamic(jp);
		}
	}

	@Test(expected = JsonParseException.class)
	public void test_DS_InvalidPartial() throws JsonParseException, IOException {
		try (JsonParser jp = parser("{")) {
			CallAllInt.DoNothingResponse.readDynamic(jp);
		}
	}

	@Test
	public void test_DS_EmptyObject() throws IOException {
		try (JsonParser jp = parser("{}")) {
			CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse
					.readDynamic(jp);
			resp.throwException();
		}
	}

	@Test
	public void test_DS_InternalException() throws IOException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.internal\", \"reason\":\"hello\"}}")) {
			CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse
					.readDynamic(jp);
			resp.throwException();
			assertTrue(false);
		} catch (InternalException ex) {
			assertEquals("hello", ex.getReason());
		}
	}

	@Test
	public void test_SZ_InternalException() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallAllInt.DoNothingResponse.writeDynamic(jg,
					new CallAllInt.DoNothingResponse()
							.withException(new InternalException()
									.withReason("hello")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.internal\",\"reason\":\"hello\"}}",
				writer.toString());
	}

	@Test
	public void test_DS_AccessDeniedException() throws IOException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.denied\", \"reason\":\"hello\"}}")) {
			CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse
					.readDynamic(jp);
			resp.throwException();
			assertTrue(false);
		} catch (AccessDeniedException ex) {
			assertEquals("hello", ex.getReason());
		}
	}

	@Test
	public void test_SZ_AccessDeniedException() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallAllInt.DoNothingResponse.writeDynamic(jg,
					new CallAllInt.DoNothingResponse()
							.withException(new AccessDeniedException()
									.withReason("hello")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.denied\",\"reason\":\"hello\"}}",
				writer.toString());
	}

	@Test
	public void test_DS_AccessExpiredException() throws IOException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.expired\", \"reason\":\"hello\"}}")) {
			CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse
					.readDynamic(jp);
			resp.throwException();
			assertTrue(false);
		} catch (AccessExpiredException ex) {
			assertEquals("hello", ex.getReason());
		}
	}

	@Test
	public void test_SZ_AccessExpiredException() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallAllInt.DoNothingResponse.writeDynamic(jg,
					new CallAllInt.DoNothingResponse()
							.withException(new AccessExpiredException()
									.withReason("hello")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.expired\",\"reason\":\"hello\"}}",
				writer.toString());
	}
	
	@Test
	public void test_DS_ValidationException() throws IOException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.validation\", \"reason\":\"hello\", \"code\":\"error\"}}")) {
			CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse
					.readDynamic(jp);
			resp.throwException();
			assertTrue(false);
		} catch (ValidationException ex) {
			assertEquals("hello", ex.getReason());
			assertEquals("error", ex.getCode());
		}
	}

	@Test
	public void test_SZ_ValidationException() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallAllInt.DoNothingResponse.writeDynamic(jg,
					new CallAllInt.DoNothingResponse()
							.withException(new ValidationException()
									.withReason("hello").withCode("error")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.validation\",\"code\":\"error\",\"reason\":\"hello\"}}",
				writer.toString());
	}
	
}
