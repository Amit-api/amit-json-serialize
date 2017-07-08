package com.amitapi.etalon;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class TestBase {
	protected static JsonFactory jfactory = new JsonFactory();

	protected static JsonGenerator generator(StringWriter writer)
			throws IOException {
		return jfactory.createGenerator(writer);
	}

	protected JsonParser parser(String str) throws JsonParseException,
			IOException {
		return jfactory.createParser(str);
	}
}
