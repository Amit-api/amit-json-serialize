package com.amitapi.etalon;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.json.SimpleTypeSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class SimpleTypeTest extends TestBase {
	private static final SimpleType orig = new SimpleType()
			.withCtype(new CustomType().withV(1))
			.withCtypesItem(new CustomType().withV(2))
			.withCtypesItem(new CustomType().withV(4))
			.withMtypesItem("1", new CustomType().withV(5))
			.withMtypesItem("2", new CustomType().withV(6));

	private final static String json = "{\"ctype\":{\"v\":1},\"ctypes\":[{\"v\":2},{\"v\":4}],\"mtypes\":{\"1\":{\"v\":5},\"2\":{\"v\":6}}}";

	@Test
	public void testSimpleTypeSerializeJson() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		SimpleTypeSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals(json, writer.toString());
	}

	@Test
	public void testSimpleTypeDeSerializeJson() throws IOException {
		JsonParser jp = parser(json);
		SimpleType obj = SimpleTypeSerializer.readDynamic(jp);
		jp.close();
		assertEquals(orig, obj);
	}
}
