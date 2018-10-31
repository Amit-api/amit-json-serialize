package com.amitapi.etalon;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import com.amitapi.etalon.json.MapPrimitiveTypesSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class MapPrimitiveTypesTest extends TestBase {
	private static LocalDateTime dateTime = LocalDateTime.of(2001, 2, 6, 3, 10,
			4);
	private static UUID uuid = UUID
			.fromString("f117adfb-6634-4ff9-bda6-dd1c8dca3380");
	private static String json = "{\"theBoolean\":{\"1\":true,\"2\":false},\"theInt\":{\"1\":10,\"2\":12},\"theLong\":{\"1\":100},"
			+ "\"theDouble\":{\"1\":2.3},\"theString\":{\"1\":\"Hello\"},\"theDate\":{\"1\":\"2001-02-06T03:10:04\"},"
			+ "\"theUiid\":{\"1\":\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"}}";

	private static MapPrimitiveTypes orig = new MapPrimitiveTypes()
			.withTheBooleanItem("1", true).withTheBooleanItem("2", false)
			.withTheIntItem("1", 10).withTheIntItem("2", 12)
			.withTheLongItem("1", 100L).withTheDoubleItem("1", 2.3)
			.withTheStringItem("1", "Hello").withTheDateItem("1", dateTime)
			.withTheUiidItem("1", uuid);
	
	
	@Test
	public void testMapPrimitiveTypesSerializeJson() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		MapPrimitiveTypesSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals(json, writer.toString());
	}

	@Test
	public void testMapPrimitiveTypesDeSerializeJson() throws IOException {
		JsonParser jp = parser(json);
		MapPrimitiveTypes obj = MapPrimitiveTypesSerializer.readDynamic(jp);
		jp.close();
		assertEquals(orig, obj);
	}
}
