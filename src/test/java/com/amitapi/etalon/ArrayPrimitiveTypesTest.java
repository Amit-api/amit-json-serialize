package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

import com.amitapi.etalon.json.ArrayPrimitiveTypesSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class ArrayPrimitiveTypesTest extends TestBase {
	private static LocalDateTime dateTime = LocalDateTime.of(2001, 2, 6, 3, 10,
			4);
	private static UUID uuid = UUID
			.fromString("f117adfb-6634-4ff9-bda6-dd1c8dca3380");
	private static String json = "{\"theBoolen\":[true,false],\"theInt\":[10,12],\"theLong\":[100],"
			+ "\"theDouble\":[2.3],\"theString\":[\"Hello\"],\"theDate\":[\"2001-02-06T03:10:04\"],"
			+ "\"theUiid\":[\"f117adfb-6634-4ff9-bda6-dd1c8dca3380\"]}";

	private static ArrayPrimitiveTypes orig = new ArrayPrimitiveTypes()
			.withTheBoolenItem(true).withTheBoolenItem(false)
			.withTheIntItem(10).withTheIntItem(12).withTheLongItem(100L)
			.withTheDoubleItem(2.3).withTheStringItem("Hello")
			.withTheDateItem(dateTime).withTheUiidItem(uuid);

	@Test
	public void testWrite() throws IOException {
		StringWriter writer = new StringWriter();
		try (JsonGenerator jp = generator(writer)) {
			ArrayPrimitiveTypesSerializer.writeDynamic(jp, orig);
		}
		assertEquals(json, writer.toString());
	}

	@Test
	public void testRead() throws IOException {
		try (JsonParser jp = parser(json)) {
			ArrayPrimitiveTypes obj = ArrayPrimitiveTypesSerializer
					.readDynamic(jp);
			assertEquals(orig, obj);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWriteBooleanArrayWitnNull() throws IOException {
		ArrayPrimitiveTypes v = new ArrayPrimitiveTypes().withTheBoolenItem(
				true).withTheBoolenItem(null);
		StringWriter writer = new StringWriter();
		try (JsonGenerator jp = generator(writer)) {
			ArrayPrimitiveTypesSerializer.writeDynamic(jp, v);
		}
	}

	@Test(expected = JsonParseException.class)
	public void testeReadArrayBooleanWithNull() throws IOException {
		try (JsonParser jp = parser("{\"theBoolen\":[true,null]}")) {
			ArrayPrimitiveTypesSerializer.readDynamic(jp);
		}
	}

	@Test
	public void testeReadNullArray() throws IOException {
		try (JsonParser jp = parser("{}")) {
			ArrayPrimitiveTypes v = ArrayPrimitiveTypesSerializer
					.readDynamic(jp);
			assertEquals(v, new ArrayPrimitiveTypes());
		}
	}

	@Test
	public void testeReadEmptyArray() throws IOException {
		try (JsonParser jp = parser("{\"theBoolen\":[],\"theInt\":[],\"theLong\":[],"
				+ "\"theDouble\":[],\"theString\":[],\"theDate\":[],"
				+ "\"theUiid\":[]}")) {
			ArrayPrimitiveTypes v = ArrayPrimitiveTypesSerializer
					.readDynamic(jp);
			assertEquals(
					v,
					new ArrayPrimitiveTypes()
							.withTheBoolen(new ArrayList<Boolean>())
							.withTheDate(new ArrayList<LocalDateTime>())
							.withTheDouble(new ArrayList<Double>())
							.withTheInt(new ArrayList<Integer>())
							.withTheLong(new ArrayList<Long>())
							.withTheString(new ArrayList<String>())
							.withTheUiid(new ArrayList<UUID>()));
		}
	}

}
