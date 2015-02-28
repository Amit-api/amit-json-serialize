package com.amitapi.etalon;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class Derived2ExceptionTest extends TestBase {

	private static final String json =  
			"{\"__type\":\"Derived2Exception\",\"v1\":1,\"v2\":2}";
	private static final BaseException orig = new Derived2Exception().
			withV2( 2 ).withV1( 1 );
	
	@Test
	public void testSerialize() throws IOException {			
		assertEquals( json, serialize( orig ) );
	}
	
	@Test
	public void testDeSerialize() throws IOException {
		BaseException obj = BaseException.__deserialize( parser( json ) );		
		assertEquals( orig, obj );
	}	

}
