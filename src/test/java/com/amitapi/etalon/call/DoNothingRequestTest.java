package com.amitapi.etalon.call;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.TestBase;
import com.amitapi.etalon.json.call.CallAllInt;
import com.amitapi.etalon.json.call.CallAllInt.DoNothingRequest;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class DoNothingRequestTest extends TestBase {
	@Test
	public void test_DS_Empty() throws JsonParseException, IOException {
		try (JsonParser jp = parser("      ")) {
			DoNothingRequest resp = CallAllInt.DoNothingRequest
					.readDynamic(jp);
			assertNotNull(resp);
		}
	}

	@Test
	public void test_SZ_Empty() throws JsonParseException, IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jg = generator(writer)) {
			CallAllInt.DoNothingRequest.writeDynamic(jg,
					new CallAllInt.DoNothingRequest());
		}

		assertEquals("", writer.toString());
	}
	
	
	@Test
	public void test_DS_EmptyObject() throws JsonParseException, IOException {
		try (JsonParser jp = parser("{}")) {
			DoNothingRequest resp = CallAllInt.DoNothingRequest
					.readDynamic(jp);
			assertNotNull(resp);
		}
	}
}
