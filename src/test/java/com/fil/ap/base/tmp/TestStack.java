package com.fil.ap.base.tmp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class TestStack {

	@Test
	public void testStack() {
		
		Stack<String> stack = new Stack<>();
		
		stack.push("item_1");
		stack.push("item_2");
		stack.push("item_3");
		stack.push("item_4");
		
		for(Iterator<String> at = stack.iterator(); at.hasNext(); ) {
			
			String msg = stack.pop();
			
			System.out.println(msg);
		}
		
	}
	
	@Test
	public void testArrayList() {
		
		List<String> list = new ArrayList<>();
		

	}
}
