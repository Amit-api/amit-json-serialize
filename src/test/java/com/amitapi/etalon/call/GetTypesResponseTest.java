package com.amitapi.etalon.call;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.amitapi.etalon.TestBase;

public class GetTypesResponseTest extends TestBase {
/*
	@Test
	public void test_SZ_EmptyObject() throws IOException {
		String result = 
				serialize( new CallInterfaceReturn.GetTypesResponse().withReturnValue( new PrimitiveTypes() ) );
		assertEquals( "{\"__type\":\"PrimitiveTypes\"}", result );
	}

	@Test
	public void test_DS_EmptyObject() throws IOException {
		CallInterfaceReturn.GetTypesResponse resp = CallInterfaceReturn.GetTypesResponse.__deserialize(
			parser( "{\"__type\":\"PrimitiveTypes\"}" ) );
		
		assertEquals( new PrimitiveTypes(), resp.getReturnValue() );
		assertNull( resp.getException() );
	}
		
	@Test
	public void test_DS_Null() throws IOException {
		CallInterfaceReturn.GetTypesResponse resp = CallInterfaceReturn.GetTypesResponse.__deserialize(
			parser("") );
		
		assertNull( resp.getReturnValue() );
		assertNull( resp.getException() );
	}
	
	@Test
	public void test_DS_UnknownObject() throws IOException {
		CallInterfaceReturn.GetTypesResponse resp =  CallInterfaceReturn.GetTypesResponse.__deserialize(
			parser("{}") );
		
		assertEquals( new PrimitiveTypes(), resp.getReturnValue() );
		assertNull( resp.getException() );
	}
	
	@Test
	public void test_SZ_BaseException() throws IOException {
		String result = serialize( 
			new CallInterfaceReturn.GetTypesResponse().withException( 
				new BaseException( "error" ) ) );
		
		assertEquals( "{\"__type\":\"BaseException\",\"__msg\":\"error\"}", result );
	}
	
	@Test
	public void test_DS_BaseException() throws IOException {
		CallInterfaceReturn.GetTypesResponse resp = CallInterfaceReturn.GetTypesResponse.__deserialize(
				parser( "{\"__type\":\"BaseException\",\"__msg\":\"error\"}" ) );
		
		assertEquals( new BaseException( "error" ), resp.getException() );
	}
	
	@Test
	public void test_SZ_MyArrayException() throws IOException {
		String result = serialize( 
			new CallInterfaceReturn.GetTypesResponse().withException( 
				new MyArrayException( "error" ) ) );
		
		assertEquals( "{\"__type\":\"MyArrayException\",\"__msg\":\"error\"}", result );
	}
	
	@Test
	public void test_DS_MyArrayException() throws IOException {
		CallInterfaceReturn.GetTypesResponse resp = CallInterfaceReturn.GetTypesResponse.__deserialize(
				parser( "{\"__type\":\"MyArrayException\",\"__msg\":\"error\"}" ) );
		
		assertEquals( new MyArrayException( "error" ), resp.getException() );
	}*/
}
