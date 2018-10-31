package com.amitapi.etalon;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.amitapi.etalon.json.ArrayPrimitiveTypesSerializer;
import com.amitapi.etalon.json.PrimitiveTypesSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SpeedTest extends TestBase {
	private static ObjectMapper mapper = new ObjectMapper();
	private static final int count;
	private static final int objCount;
	private static final int warmUpCount;
	private static final String deserialize;
	private static PrimitiveTypes[] array;
	private static String[] strings;

	static {
		objCount = 100;
		count = 10000000;
		warmUpCount = 10000;
		deserialize = 
			"{\"theBoolean\":true,\"theUiid\":null,\"theDate\":null,\"theLong\":%s,\"theString\":\"Hello\"," +
					"\"theDouble\":0.0,\"theInt\":%s}";
		array = new PrimitiveTypes[objCount];
		strings = new String[objCount];

		for (int i = 0; i < objCount; i++) {
			array[i] = new PrimitiveTypes().withTheBoolean(true).withTheInt(i)
					.withTheLong((long) i).withTheDouble((double) i)
					.withTheString("Hello");
		}		

		for (int i = 0; i < objCount; i++) {
			strings[i] = String.format(deserialize, i, i);
		}
	}

	//@Test
	public void test_SerializeMine() throws IOException {
		long start = 0;
		int hash = 0;

		for (int i = 0; i < count + warmUpCount; i++) {
			if( i == warmUpCount ) {
				start = System.currentTimeMillis();
			}
			SegmentedStringWriter sw = new SegmentedStringWriter(
					jfactory._getBufferRecycler());

			JsonGenerator jp = jfactory.createGenerator(sw);
			PrimitiveTypesSerializer.writeDynamic(jp, array[i % objCount]);
			jp.close();
			//System.out.print(sw.getAndClear());
			hash += sw.getAndClear().hashCode();
		}

		System.out.printf("\n**Mine -> time: %s, %s", System.currentTimeMillis()
				- start, hash);
	}
	
	//@Test
	public void test_SerializeJacson() throws IOException {
		
		/////////////////////////////////////////////////////
		long start = System.currentTimeMillis();
		int hash = 0;
		for (int i = 0; i < count; i++) {
			String result = mapper.writeValueAsString(array[i%objCount]);
			//System.out.print(result);
			hash += result.hashCode();
		}
		System.out.printf("\n**Jacson -> time: %s, %s", System.currentTimeMillis()
				- start, hash);
	}

	//@Test
	public void test_DeSerializeJacson() throws IOException {
		
		long start = System.currentTimeMillis();
		long hash = 0;

		for (int i = 0; i < count + warmUpCount; i++) {
			if( i == warmUpCount ) {
				start = System.currentTimeMillis();
			}		
			JsonParser jp = parser(strings[i%objCount]);
			PrimitiveTypes v = mapper.readValue(jp, PrimitiveTypes.class );
			jp.close();
		}
	
		System.out.printf("\n**Jacson <- time: %s", System.currentTimeMillis()
				- start);
	}

	//@Test
	public void test_DeSerializeMine() throws IOException {
		
		long start = System.currentTimeMillis();
		long hash = 0;

		for (int i = 0; i < count + warmUpCount; i++) {
			if( i == warmUpCount ) {
				start = System.currentTimeMillis();
			}			
			JsonParser jp = parser(strings[i%objCount]);
			PrimitiveTypesSerializer.readDynamic(jp);
			jp.close();
		}
	
		System.out.printf("\n**Mine <- time: %s", System.currentTimeMillis()
				- start);
	}
}
