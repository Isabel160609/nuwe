package com.init.User.Model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

@Document(collection = "User")
public class User implements Serializable {

	@Id // {unique, required}
	@Field(name = "username")
	private String username;

	@Field(name = "age")
	private int age;

	@Field(name = "city")
	private String city;

	public User(String username, int age, String city) {
		this.username = username;
		this.age = age;
		this.city = city;
	}

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", city=" + city + "]";
	}

}
