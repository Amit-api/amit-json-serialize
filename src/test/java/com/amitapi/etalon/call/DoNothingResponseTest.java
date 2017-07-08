package com.amitapi.etalon.call;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.amitapi.etalon.TestBase;

public class DoNothingResponseTest extends TestBase {

	/*
	@Test
	public void test_DS_Empty() throws JsonParseException, IOException {
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize( 
			parser( "      " ) );
		assertNull( resp.getException() );
	}

	@Test
	public void test_SZ_Empty() throws JsonParseException, IOException {
		assertEquals( "", serialize( new CallAllInt.DoNothingResponse() ));
	}

	@Test
	public void test_DS_EmptyMin() throws JsonParseException, IOException {
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize(
			parser( "" ) );
		assertNull( resp.getException() );
	}
	
	@Test( expected = IOException.class )
	public void test_DS_InvalidImput() throws JsonParseException, IOException {
		CallAllInt.DoNothingResponse.__deserialize( 
			parser( "jfksldk" ) );		
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void test_DS_EmptyObject() throws IOException {
		CallAllInt.DoNothingResponse.__deserialize( 
			parser( "{}" ) );				
	}
	
	@Test
	public void test_DS_AmitInternalErrorException() throws IOException {
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize(
			parser( "{\"__type\":\"exception.internal\", \"__msg\":\"hello\"}" ));
		assertNotNull( resp.getException() );		
		AmitInternalErrorException excep = (AmitInternalErrorException)resp.getException();
		assertEquals( "hello", excep.getMessage() );
	}
	
	@Test
	public void test_SZ_AmitInternalErrorException() throws IOException {
		assertEquals( "{\"__type\":\"exception.internal\",\"__msg\":\"error\"}", 
				serialize( new CallAllInt.DoNothingResponse().
						withException( new AmitInternalErrorException( "error" ) ) ));
	}

	@Test
	public void test_DS_AmitInvalidRequestException() throws IOException {
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize(
			parser( "{\"__type\":\"exception.request\",\"__msg\":\"hello\"}" ));
		assertNotNull( resp.getException() );
		AmitInvalidRequestException excep = (AmitInvalidRequestException)resp.getException();
		assertEquals( "hello", excep.getMessage() );
	}

	@Test
	public void test_SZ_AmitInvalidRequestException() throws IOException {
		assertEquals( "{\"__type\":\"exception.request\",\"__msg\":\"error\"}", 
				serialize( new CallAllInt.DoNothingResponse().
						withException( new AmitInvalidRequestException( "error") ) ));	
	}
	
	@Test
	public void test_DS_AmitInvalidResponseException() throws IOException {
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize(
			parser( "{\"__type\":\"exception.response\", \"__msg\":\"hello\"}" ) );
		assertNotNull( resp.getException() );		
		AmitInvalidResponseException excep = (AmitInvalidResponseException)resp.getException();
		assertEquals( "hello", excep.getMessage() );
	}

	@Test
	public void test_SZ_AmitInvalidResponseException() throws IOException {
		assertEquals( "{\"__type\":\"exception.response\",\"__msg\":\"error\"}", 
				serialize( new CallAllInt.DoNothingResponse().
						withException( new AmitInvalidResponseException( "error") ) ));		
	}
	
	@Test
	public void test_DS_AmitRuntimeException() throws IOException {
		CallAllInt.DoNothingResponse resp = CallAllInt.DoNothingResponse.__deserialize( 
			parser( "{\"__type\":\"exception\", \"__msg\":\"hello\"}" ) );
		assertNotNull( resp.getException() );
		AmitRuntimeException excep = (AmitRuntimeException)resp.getException();
		assertEquals( "hello", excep.getMessage() );
	}

	@Test
	public void test_SZ_AmitRuntimeException() throws IOException {
		assertEquals( "{\"__type\":\"exception\",\"__msg\":\"error\"}", 
				serialize( new CallAllInt.DoNothingResponse().
						withException( new AmitRuntimeException( "error") ) ));		
	}*/
}
