package com.fil.ap.base.tmp;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestExceptionStack {

	public static void main(String args[]) {
		
		try{
			String source = "dfdfd888";
			
			String target = new String(source.getBytes(), "KKOO");
			
			System.out.println(target);
		} catch(Exception e) {
			
			Long timeMilli = System.currentTimeMillis();
			
			RuntimeException ee = new RuntimeException("Runtime Exception", e);
			StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
			ee.printStackTrace(writer);
			StringBuffer buffer = stringWriter.getBuffer();
	        String log = buffer.toString();
	        
	        System.out.println(timeMilli + "-" + log);
		}
	}
}
