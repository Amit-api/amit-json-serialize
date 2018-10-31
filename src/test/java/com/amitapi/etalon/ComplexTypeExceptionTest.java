package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.json.ComplexTypeExceptionSerializer;
import com.fasterxml.jackson.core.JsonGenerator;

public class ComplexTypeExceptionTest extends TestBase {
	private static final String json = "{\"__type\":\"ComplexTypeException\",\"base\":"
			+ "{\"__type\":\"Derived2\",\"v2\":2,\"v1\":1},"
			+ "\"moreBase\":"
			+ "[{\"__type\":\"Derived1\",\"v2\":3,\"v1\":4}]}";

	private static final ComplexTypeException orig = new ComplexTypeException()
			.withBase(new Derived2().withV2(2).withV1(1)).withMoreBaseItem(
					new Derived1().withV2(3).withV1(4));

	@Test
	public void testSerializeJson() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		ComplexTypeExceptionSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals(json, writer.toString());
	}

	@Test
	public void testDeSerializeJson() throws IOException {
		ComplexTypeException obj = ComplexTypeExceptionSerializer
				.readDynamic(parser(json));
		assertEquals(orig, obj);
	}
}
