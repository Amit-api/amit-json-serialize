package com.amitapi.etalon.call;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.amitapi.etalon.TestBase;

public class GetAllTypesResponseTest extends TestBase {
/*
	@Test
	public void test_DS_Null() throws IOException {
		CallInterfaceReturn.GetAllTypesResponse resp = CallInterfaceReturn.GetAllTypesResponse.__deserialize(
			parser("") );
		
		assertNull( resp.getReturnValue() );
		assertNull( resp.getException() );
	}

	@Test
	public void test_SZ_Null() throws IOException {
		String result = serialize( new CallInterfaceReturn.GetAllTypesResponse() );		
		assertEquals( "", result );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void test_DS_EmptyObject() throws IOException {
		CallInterfaceReturn.GetAllTypesResponse resp = CallInterfaceReturn.GetAllTypesResponse.__deserialize(
			parser("{}") );
		
		assertNull( resp.getReturnValue() );
		assertNull( resp.getException() );
	}

	@Test
	public void test_DS_EmptyArray() throws IOException {
		CallInterfaceReturn.GetAllTypesResponse resp = CallInterfaceReturn.GetAllTypesResponse.__deserialize(
			parser("[]") );
		
		assertNotNull( resp.getReturnValue() );
		assertEquals( 0, resp.getReturnValue().size() );
		assertNull( resp.getException() );
	}
	
	@Test
	public void test_SZ_EmptyArray() throws IOException {
		String result = serialize( new CallInterfaceReturn.GetAllTypesResponse().
				withReturnValue( new ArrayList<PrimitiveTypes>() ) );		
		assertEquals( "[]", result );		
	}
	
	@Test
	public void test_DS_Array() throws IOException {
		CallInterfaceReturn.GetAllTypesResponse resp = CallInterfaceReturn.GetAllTypesResponse.__deserialize(
			parser("[{\"__type\":\"PrimitiveTypes\"}]") );
		
		assertNotNull( resp.getReturnValue() );
		assertEquals( 1, resp.getReturnValue().size() );
		assertEquals( new PrimitiveTypes(), resp.getReturnValue().get( 0 ) );
		assertNull( resp.getException() );
	}
	
	@Test
	public void test_SZ_Array() throws IOException {
		@SuppressWarnings("serial")
		String result = serialize( new CallInterfaceReturn.GetAllTypesResponse().
				withReturnValue( new ArrayList<PrimitiveTypes>() {{ add( new PrimitiveTypes() ); } }) );
		
		assertEquals( "[{\"__type\":\"PrimitiveTypes\"}]", result );
	
	}
	
	
	@Test
	public void test_SZ_BaseException() throws IOException {
		String result = serialize( 
			new CallInterfaceReturn.GetAllTypesResponse().withException( 
				new BaseException( "error" ) ) );
		
		assertEquals( "{\"__type\":\"BaseException\",\"__msg\":\"error\"}", result );
	}
	
	@Test
	public void test_DS_BaseException() throws IOException {
		CallInterfaceReturn.GetAllTypesResponse resp = CallInterfaceReturn.GetAllTypesResponse.__deserialize(
				parser( "{\"__type\":\"BaseException\",\"__msg\":\"error\"}" ) );
		
		assertEquals( new BaseException( "error" ), resp.getException() );
	}
	
	@Test
	public void test_SZ_MyArrayException() throws IOException {
		String result = serialize( 
			new CallInterfaceReturn.GetAllTypesResponse().withException( 
				new MyArrayException( "error" ) ) );
		
		assertEquals( "{\"__type\":\"MyArrayException\",\"__msg\":\"error\"}", result );
	}
	
	@Test
	public void test_DS_MyArrayException() throws IOException {
		CallInterfaceReturn.GetAllTypesResponse resp = CallInterfaceReturn.GetAllTypesResponse.__deserialize(
				parser( "{\"__type\":\"MyArrayException\",\"__msg\":\"error\"}" ) );
		
		assertEquals( new MyArrayException( "error" ), resp.getException() );
	}	*/
	
}
