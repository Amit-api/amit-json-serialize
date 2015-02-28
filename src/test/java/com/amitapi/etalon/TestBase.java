package com.amitapi.etalon;

import java.io.IOException;
import java.io.StringWriter;

import com.amitapi.json.runtime.__JsonSerializable;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class TestBase {
	private static JsonFactory jfactory = new JsonFactory();

	protected static String serialize( __JsonSerializable obj ) throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator j = jfactory.createGenerator( writer );
		obj.__serialize( j );
		j.close();
		return writer.toString();
	}
	
	protected JsonParser parser( String str ) throws JsonParseException, IOException {
		return jfactory.createParser( str );
	}
}
