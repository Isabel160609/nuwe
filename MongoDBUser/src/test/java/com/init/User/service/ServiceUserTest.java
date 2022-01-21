package com.init.User.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import com.init.User.Dao.UserDao;
import com.init.User.Model.User;
import com.init.User.Service.ServiceUser;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = ServiceUserTest.class)
public class ServiceUserTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	ServiceUser serviceUser;

	List<User> users;;
	User user1;
	User user2;
	User user3;

	@Test
	@Order(1)
	public void test_listUsers() {
		user1 = new User("Isa", 44, "Barcelona");
		user2 = new User("Ana", 46, "LLeida");

		users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);

		when(userDao.findAll()).thenReturn(users);
		assertEquals(2, serviceUser.listUsers().size());
		assertEquals("Isa", serviceUser.listUsers().get(0).getUsername());

	}

	@Test
	@Order(2)
	public void test_creaJogador() {
		user3 = new User("Laura", 44, "Barcelona");

		when(userDao.save(user3)).thenReturn(user3);
		assertEquals(user3, serviceUser.createUser(user3));
	}

	@Test
	@Order(3)
	public void test_DeleteUser() {
		user3 = new User("Laura", 44, "Barcelona");
		String username = "Laura";
		when(userDao.findById(username)).thenReturn(Optional.of(user3));
		serviceUser.DeleteUser(username);
		verify(userDao, times(1)).delete(user3);
		assertEquals("Usuario borrado",serviceUser.DeleteUser(username));

	}
	
	@Test
	@Order(3)
	public void test_DeleteUsernotFound() {
		user3 = new User("Laura", 44, "Barcelona");
		String username = "Laura";
		when(userDao.findById(username)).thenReturn(Optional.empty());
		serviceUser.DeleteUser(username);
		assertEquals("este usuario no existe",serviceUser.DeleteUser(username));

	}

	@Test
	@Order(4)
	public void test_getUser() {

		user1 = new User("Isa", 44, "Barcelona");
		Optional<User> Optionaluser=Optional.ofNullable(user1);
		String username = "Isa";
		when(userDao.findById(username)).thenReturn(Optional.of(user1));
		assertEquals(Optionaluser, serviceUser.getUser(username));
	}

	@Test
	@Order(5)
	public void test_updateUser() {

		user1 = new User("Isa", 44, "Barcelona");
		User userModificado = new User("Isa", 48, "lleida");
		String username = "Isa";
		when(userDao.findById(username)).thenReturn(Optional.of(user1));
		when(userDao.save(userModificado)).thenReturn(userModificado);
		assertEquals("este usuario ha sio modificado", serviceUser.updateUser(username, user1));
	}
	
	@Test
	@Order(5)
	public void test_updateUsernotFound() {

		user1 = new User("Isa", 44, "Barcelona");
		String username = "Isa";
		when(userDao.findById(username)).thenReturn(Optional.empty());
		assertEquals("este usuario no existe", serviceUser.updateUser(username, user1));
	}
}
