package com.fil.ap.base.pojo;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;
    
    private int age;
    private String name;
    private String sex;
    private byte[] thumbnail;
    
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}
    
    
}
