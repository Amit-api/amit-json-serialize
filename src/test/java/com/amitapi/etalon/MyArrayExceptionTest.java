package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import com.amitapi.etalon.json.MyArrayExceptionSerializer;
import com.fasterxml.jackson.core.JsonGenerator;

public class MyArrayExceptionTest extends TestBase {
	private static LocalDateTime dateTime = LocalDateTime.of(2001, 2, 6, 3, 10,
			4);
	private static UUID uuid = UUID
			.fromString("f117adfb-6634-4ff9-bda6-dd1c8dca3380");
	private static String json = "{\"__type\":\"MyArrayException\",\"theBoolean\":[true,false],\"theInt\":[10,12],\"theLong\":[100],"
			+ "\"theDouble\":[2.3],\"theString\":[\"Hello\"],\"theDate\":[\"2001-02-06T03:10:04\"],"
			+ "\"theUiid\":[\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"]}";

	private static MyArrayException orig = new MyArrayException()
			.withTheBooleanItem(true).withTheBooleanItem(false)
			.withTheIntItem(10).withTheIntItem(12).withTheLongItem(100L)
			.withTheDoubleItem(2.3).withTheStringItem("Hello")
			.withTheDateItem(dateTime).withTheUiidItem(uuid);

	@Test
	public void testSerializeJson() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		MyArrayExceptionSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals(json, writer.toString());
	}

	@Test
	public void testDeSerializeJson() throws IOException {
		MyArrayException obj = MyArrayExceptionSerializer
				.readDynamic(parser(json));
		assertEquals(orig, obj);
	}
}
