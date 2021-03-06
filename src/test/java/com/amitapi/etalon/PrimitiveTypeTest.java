package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

import com.amitapi.etalon.json.PrimitiveTypesSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class PrimitiveTypeTest extends TestBase {
	private static LocalDateTime dateTime = LocalDateTime.of(2001, 2, 6, 3, 10,
			4);
	private static UUID uuid = UUID
			.fromString("f117adfb-6634-4ff9-bda6-dd1c8dca3380");
	private static String json = "{\"theBoolean\":true,\"theInt\":10,\"theLong\":100,"
			+ "\"theDouble\":2.3,\"theString\":\"Hello\",\"theDate\":\"2001-02-06T03:10:04\","
			+ "\"theUiid\":\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"}";

	private static PrimitiveTypes orig = new PrimitiveTypes()
			.withTheBoolean(true).withTheInt(10).withTheLong(100L)
			.withTheDouble(2.3).withTheString("Hello").withTheDate(dateTime)
			.withTheUiid(uuid);

	@Test
	public void testWrite() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator jp = generator(writer);
		PrimitiveTypesSerializer.writeDynamic(jp, orig);
		jp.close();
		assertEquals(json, writer.toString());
	}

	@Test
	public void testRead() throws IOException {
		JsonParser jp = parser(json);
		PrimitiveTypes obj = PrimitiveTypesSerializer.readDynamic(jp);
		jp.close();
		assertEquals(orig, obj);
	}

	@Test(expected = JsonParseException.class)
	public void testRead_badInt() throws IOException {
		JsonParser jp = parser("{\"theInt\":\"10\"}");
		PrimitiveTypesSerializer.readDynamic(jp);
	}

	@Test(expected = JsonParseException.class)
	public void testRead_badBoolean() throws IOException {
		JsonParser jp = parser("{\"theBoolean\":1}");
		PrimitiveTypesSerializer.readDynamic(jp);
	}
	
	@Test 
	public void testRead_nullValue() throws IOException {		
		JsonParser jp = parser("{\"theBoolean\":null}");
		PrimitiveTypes r = PrimitiveTypesSerializer.readDynamic(jp);
		assertNull(r.getTheBoolean());
		jp.close();
	}
	

	@Test
	public void testPrimitiveDeSerializeJson_DirtyJson()
			throws JsonParseException, IOException {
		JsonParser jp = parser("{\"stuf\": { \"o\":1 }, \"array\":[1,2,3], \"theBoolean\":true, \"more\": [1,2]}");
		assertEquals(new PrimitiveTypes().withTheBoolean(true),
				PrimitiveTypesSerializer.readDynamic(jp));
	}

	@Test(expected = JsonParseException.class)
	public void testPrimitiveDeSerializeJson_PartialJson()
			throws JsonParseException, IOException {
		JsonParser jp = parser("{\"stuf\": { \"o\":1 }, \"array\":[1,2,3], \"theBoolean\":true, \"more\": [1,2] ");
		assertEquals(new PrimitiveTypes().withTheBoolean(true),
				PrimitiveTypesSerializer.readDynamic(jp));
	}
}
