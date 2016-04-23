package com.fil.ap.base.redis;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.fil.ap.base.pojo.Person;
import com.fil.ap.util.SerializeUtil;

import redis.clients.jedis.Jedis;

public class TestString {

	/**
	 * Simple String get / set
	 */
	@Test
	public void testSimpleString() {
		
		Jedis conn = new Jedis("localhost", 6379);
		conn.select(0);
		
		String key = "string:simplestring";
		String value = "Hello World!";
		
		conn.set(key, value);
		
		String retrievedValue = conn.get(key);
		
		System.out.println(retrievedValue);
		
		conn.close();
	}
	
	/**
	 * String byte array get / set
	 */
	@Test
	public void testByteArrayString() {
		
		Jedis conn = new Jedis("localhost", 6379);
		conn.select(0);
		
		String key = "string:bytearraystring";
		String value = "This is an byte array String!";
		
		conn.set(key.getBytes(), value.getBytes());
		
		String retrievedValue = conn.get(key);
		
		System.out.println(retrievedValue);
		
		conn.close();
	}
	
	/**
	 * Object serialization string get / set
	 * 
	 */
	@Test
	public void testObjectSerialization() {
		
		Jedis conn = new Jedis("localhost", 6379);
		conn.select(0);
		
		String key = "string:objectserializationstring";
		
		Person person = new Person();
		person.setName("Alex");
		person.setAge(25);
		person.setSex("男");
		person.setThumbnail(new String("thumbernail").getBytes());
		
		byte[] value = SerializeUtil.serialize(person);
		
		byte[] fbytes64 = Base64.getEncoder().encode(value);
		
		conn.set(key.getBytes(), fbytes64);
		
		byte[] retrievedValue64 = conn.get(key.getBytes());
		
		byte[] retrievedValue = Base64.getDecoder().decode(retrievedValue64);
		
		Person retrievedPerson = (Person)SerializeUtil.unserialize(retrievedValue);
		
		String retrievedPersonJson = JSON.toJSONString(retrievedPerson);
		
		System.out.println("String = " + retrievedValue);
		
		System.out.println("Jason = " + retrievedPersonJson);
		conn.close();
	}
	
	/**
	 * Object Json string get / set
	 * 
	 * 
	 */
	@Test
	public void testObjectJason() {
		
		Jedis conn = new Jedis("localhost", 6379);
		conn.select(0);
		
		String key = "string:objectjson";
		
		Person person = new Person();
		person.setName("Alex");
		person.setAge(25);
		person.setSex("男");
		person.setThumbnail(new String("thumbernail").getBytes());
		
		byte[] value = JSON.toJSONString(person).getBytes();
		
		conn.set(key.getBytes(), value);
		
		byte[] retrievedValue = conn.get(key.getBytes());
		
		String jsonString = new String(retrievedValue);
		
		Person personObject = JSON.parseObject(jsonString, Person.class);

		System.out.println("Jason = " + jsonString);
		
		System.out.println(MessageFormat.format("name={0},age={1},sex={2}", personObject.getName(), personObject.getAge(), personObject.getSex()));
		
		conn.close();
	}
	
	/**
	 * File byte array get / set
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFileByteArray() throws IOException {
		
		Jedis conn = new Jedis("localhost", 6379);
		conn.select(0);
		
		String key = "string:filebytearray";
		
		InputStream fis = new FileInputStream("d:/data/src_tif1.tif");
		
		byte[] fbytes = IOUtils.toByteArray(fis);
		
		conn.set(key.getBytes(), fbytes);
		
		byte[] retrievedValue = conn.get(key.getBytes());
		
		OutputStream fos = new FileOutputStream("d:/data/src_tif1_out.tif");
		
		IOUtils.write(retrievedValue, fos);
		
		conn.close();
	}
	
	/**
	 * File base64byte array get / set
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFileBase64() throws IOException {
		
		Jedis conn = new Jedis("localhost", 6379);
		conn.select(0);
		
		String key = "string:filebase64";
		
		InputStream fis = new FileInputStream("d:/data/redis.jpg");
		
		byte[] fbytes = IOUtils.toByteArray(fis);
		
		byte[] fbytes64 = Base64.getEncoder().encode(fbytes);
		
		conn.set(key.getBytes(), fbytes64);
		
		byte[] retrievedValue64 = conn.get(key.getBytes());
		
		byte[] retrievedValue = Base64.getDecoder().decode(retrievedValue64);
		
		OutputStream fos = new FileOutputStream("d:/data/redis_out.jpg");
		
		IOUtils.write(retrievedValue, fos);
		
		System.out.println("Base64 In = " + new String(fbytes64));
		
		System.out.println("Base64 Out = " + new String(retrievedValue64));
		
		conn.close();
	}
}
