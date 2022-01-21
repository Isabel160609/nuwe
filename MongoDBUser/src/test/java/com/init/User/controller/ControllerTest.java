package com.init.User.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.init.User.Controller.ControllerUser;
import com.init.User.Model.User;
import com.init.User.Service.ServiceUser;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ControllerTest.class })
public class ControllerTest {

	@Mock
	private ServiceUser serviceUser;

	@InjectMocks
	ControllerUser controllerUser;

	List<User> users;;
	User user1;
	User user2;
	User user3;

	@Test
	@Order(1)
	public void test_getUsersFound() {
		user1 = new User("Isa", 44, "Barcelona");
		user2 = new User("Ana", 46, "LLeida");

		users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		when(serviceUser.listUsers()).thenReturn(users);
		ResponseEntity<List<User>> res = controllerUser.getUsers();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2, res.getBody().size());
	}

	@Test
	@Order(1)
	public void test_getUsersNotFound() {
		when(serviceUser.listUsers()).thenThrow();
		ResponseEntity<List<User>> res = controllerUser.getUsers();
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

	}

	@Test
	@Order(2)
	public void test_createUserCreated() {
		user1 = new User("Isa", 44, "Barcelona");
		
		when(serviceUser.createUser(user1)).thenReturn(user1);
		ResponseEntity<User> res = controllerUser.createUser(user1);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(user1, res.getBody());
	}

	@Test
	@Order(2)
	public void test_createUserComflict() {

		when(serviceUser.createUser(null)).thenThrow();
		ResponseEntity<User> res = controllerUser.createUser(null);
		assertEquals(HttpStatus.CONFLICT, res.getStatusCode());

	}

	@Test
	@Order(3)
	public void test_deleteUserOk() {

		user1 = new User("Isa", 44, "Barcelona");
		String username="Isa";
		when(serviceUser.DeleteUser(username)).thenReturn(username);
		ResponseEntity<String> res = controllerUser.deleteUser(username);
		assertEquals(HttpStatus.OK, res.getStatusCode());
	
	}
	@Test
	@Order(3)
	public void test_deleteUserConflict() {
	
		when(serviceUser.DeleteUser(null)).thenThrow();
		ResponseEntity<String> res = controllerUser.deleteUser(null);
		assertEquals(HttpStatus.CONFLICT, res.getStatusCode());
		
	}
	
	
	@Test
	@Order(4)
	public void test_getUserOk() {
		user1 = new User("Isa", 44, "Barcelona");
		Optional<User> Optionaluser=Optional.ofNullable(user1);
		String username="Isa";
		when(serviceUser.getUser(username)).thenReturn(Optionaluser);
		ResponseEntity<Optional<User>> res = controllerUser.getUser(username);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		
	}
	
	
	@Test
	@Order(4)
	public void test_getUserConflict() {
		
		when(serviceUser.getUser(null)).thenThrow();
		ResponseEntity<Optional<User>> res = controllerUser.getUser(null);
		assertEquals(HttpStatus.CONFLICT, res.getStatusCode());
	}
	
	
	@Test
	@Order(5)
	public void test_updateUsersOk() {
		user1 = new User("Isa", 44, "Barcelona");
		String username="Isa";
		String userUpdate = "este usuario ha sio modificado";
		
		when(serviceUser.updateUser(username,user1)).thenReturn(userUpdate);
		ResponseEntity<String> res = controllerUser.updateUsers(username,user1);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(userUpdate, res.getBody());
		
	}
	
	@Test
	@Order(5)
	public void test_updateUsersConflict() {
		
		when(serviceUser.updateUser(null,null)).thenThrow();
		ResponseEntity<String> res = controllerUser.updateUsers(null,null);
		assertEquals(HttpStatus.CONFLICT, res.getStatusCode());
		
	}
	
	
	
	
	
}
