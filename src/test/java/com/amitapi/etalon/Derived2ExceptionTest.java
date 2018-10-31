package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.json.BaseExceptionSerializer;
import com.fasterxml.jackson.core.JsonGenerator;

public class Derived2ExceptionTest extends TestBase {

	private static final String json = "{\"__type\":\"Derived2Exception\",\"v2\":2,\"v1\":1}";
	private static final BaseException orig = new Derived2Exception().withV2(2)
			.withV1(1);

	@Test
	public void testSerialize() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		BaseExceptionSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals(json, writer.toString());
	}

	@Test
	public void testDeSerialize() throws IOException {
		BaseException obj = BaseExceptionSerializer.readDynamic(parser(json));
		assertEquals(orig, obj);
	}
}
