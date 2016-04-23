package com.fil.ap.base.tmp;

import java.util.Base64;

import org.junit.Test;

public class TestString {

	@Test
	public void testEncoding() {
		
		byte[] bytes = new String("thumbernail").getBytes();
		
		System.out.println(bytes);
		
		byte[] bytes64 = Base64.getEncoder().encode(bytes);
		
		System.out.println(new String(bytes64));
	}
}
