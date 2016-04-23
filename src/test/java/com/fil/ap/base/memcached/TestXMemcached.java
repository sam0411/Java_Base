package com.fil.ap.base.memcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.fil.ap.base.pojo.Person;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;


public class TestXMemcached {

	@Test
	public void testSimpleString() throws IOException, TimeoutException, InterruptedException, MemcachedException {
		
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:11211"));
		MemcachedClient memcachedClient = builder.build();
		
		String key = "simplestring";
		String value = "Hello World Memcached !";
        memcachedClient.set(
        		key, 
        		0, 
        		value
        );
        
        String retrievedValue = memcachedClient.get(key);
        
        System.out.println("Value = "+retrievedValue);
	}
	
	@Test
	public void testObjectSerialization() throws IOException, TimeoutException, InterruptedException, MemcachedException {
		
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:11211"));
		MemcachedClient memcachedClient = builder.build();
		
		String key = "simpleobject";
		
		Person person = new Person();
		person.setName("Alex");
		person.setAge(25);
		person.setSex("男");
		person.setThumbnail(new String("thumbernail").getBytes());
		
        memcachedClient.set(
        		key, 
        		0, 
        		person
        );
        
        Person retrievedPerson = memcachedClient.get(key);
        
        String retrievedPersonJson = JSON.toJSONString(retrievedPerson);

		System.out.println("Jason = " + retrievedPersonJson);
		
		String retrievedString = memcachedClient.get(key);
		
		System.out.println("String = " + retrievedPersonJson);
	}
}
