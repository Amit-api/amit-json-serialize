package com.amitapi.etalon.call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import org.junit.Test;

import com.amitapi.etalon.BaseException;
import com.amitapi.etalon.MyArrayException;
import com.amitapi.etalon.TestBase;
import com.amitapi.etalon.exception.AccessDeniedException;
import com.amitapi.etalon.exception.AccessExpiredException;
import com.amitapi.etalon.exception.InternalException;
import com.amitapi.etalon.exception.ValidationException;
import com.amitapi.etalon.json.call.CallInterfaceReturn;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class ReturnLongResponseTest extends TestBase {
	@Test
	public void test_DS_Empty() throws JsonParseException, IOException,
			BaseException, MyArrayException {
		try (JsonParser jp = parser("      ")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
					.readDynamic(jp);
			resp.throwException();
		}
	}

	@Test
	public void test_SZ_Empty() throws JsonParseException, IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse());
		}

		assertEquals("{}", writer.toString());
	}

	@Test
	public void test_DS_EmptyMin() throws JsonParseException, IOException,
			BaseException, MyArrayException {
		try (JsonParser jp = parser("")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
					.readDynamic(jp);
			resp.throwException();
		}
	}

	@Test(expected = JsonParseException.class)
	public void test_DS_InvalidImput() throws JsonParseException, IOException {
		try (JsonParser jp = parser("hello")) {
			CallInterfaceReturn.ReturnLongResponse.readDynamic(jp);
		}
	}

	@Test(expected = JsonParseException.class)
	public void test_DS_InvalidPartial() throws JsonParseException, IOException {
		try (JsonParser jp = parser("{")) {
			CallInterfaceReturn.ReturnLongResponse.readDynamic(jp);
		}
	}

	@Test
	public void test_DS_EmptyObject() throws IOException, BaseException,
			MyArrayException {
		try (JsonParser jp = parser("{}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
					.readDynamic(jp);
			resp.throwException();
		}
	}

	@Test
	public void test_DS_InternalException() throws IOException, BaseException,
			MyArrayException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.internal\", \"reason\":\"hello\"}}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
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
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withException(new InternalException()
									.withReason("hello")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.internal\",\"reason\":\"hello\"}}",
				writer.toString());
	}

	@Test
	public void test_DS_AccessDeniedException() throws IOException,
			BaseException, MyArrayException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.denied\", \"reason\":\"hello\"}}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
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
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withException(new AccessDeniedException()
									.withReason("hello")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.denied\",\"reason\":\"hello\"}}",
				writer.toString());
	}

	@Test
	public void test_DS_AccessExpiredException() throws IOException,
			BaseException, MyArrayException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.expired\", \"reason\":\"hello\"}}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
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
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withException(new AccessExpiredException()
									.withReason("hello")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.expired\",\"reason\":\"hello\"}}",
				writer.toString());
	}

	@Test
	public void test_DS_BaseException() throws IOException, BaseException,
			MyArrayException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"BaseException\",\"v1\":10}}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
					.readDynamic(jp);
			resp.throwException();
			assertTrue(false);
		} catch (BaseException ex) {
			assertEquals(new Integer(10), ex.getV1());
		}
	}

	@Test
	public void test_SZ_BaseException() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withException(new BaseException().withV1(10)));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"BaseException\",\"v1\":10}}",
				writer.toString());
	}

	@Test
	public void test_DS_MyArrayException() throws IOException, BaseException,
			MyArrayException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"MyArrayException\",\"theBoolean\":[true,false]}}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
					.readDynamic(jp);
			resp.throwException();
			assertTrue(false);
		} catch (MyArrayException ex) {
			assertEquals(Arrays.asList(true, false), ex.getTheBoolean());
		}
	}

	@Test
	public void test_SZ_MyArrayException() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withException(new MyArrayException()
									.withTheBooleanItem(true)
									.withTheBooleanItem(false)));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"MyArrayException\",\"theBoolean\":[true,false]}}",
				writer.toString());
	}

	@Test
	public void test_DS_ValidationException() throws IOException,
			BaseException, MyArrayException {
		try (JsonParser jp = parser("{\"exception\":{\"__type\":\"exception.validation\", \"reason\":\"hello\", \"code\":\"error\"}}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
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
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withException(new ValidationException()
									.withReason("hello").withCode("error")));
		}

		assertEquals(
				"{\"exception\":{\"__type\":\"exception.validation\",\"code\":\"error\",\"reason\":\"hello\"}}",
				writer.toString());
	}

	@Test
	public void test_DS_returnValue_Null() throws JsonParseException,
			IOException, BaseException, MyArrayException {
		try (JsonParser jp = parser("{\"result\": null}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
					.readDynamic(jp);
			resp.throwException();
			assertNull(resp.getReturnValue());
		}
	}

	@Test
	public void test_SZ_returnValue_Null() throws JsonParseException,
			IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withReturnValue(null));
		}

		assertEquals("{}", writer.toString());
	}

	@Test
	public void test_DS_returnValue_HasValue() throws JsonParseException,
			IOException, BaseException, MyArrayException {
		try (JsonParser jp = parser("{\"result\": 10}")) {
			CallInterfaceReturn.ReturnLongResponse resp = CallInterfaceReturn.ReturnLongResponse
					.readDynamic(jp);
			resp.throwException();
			assertEquals(new Long(10), resp.getReturnValue());
		}
	}

	@Test
	public void test_SZ_returnValue_HasValue() throws JsonParseException,
			IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallInterfaceReturn.ReturnLongResponse.writeDynamic(jg,
					new CallInterfaceReturn.ReturnLongResponse()
							.withReturnValue(10L));
		}

		assertEquals("{\"result\":10}", writer.toString());
	}
}
