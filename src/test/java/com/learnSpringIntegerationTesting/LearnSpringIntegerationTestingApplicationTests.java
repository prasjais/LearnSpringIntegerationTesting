package com.learnSpringIntegerationTesting;

import com.learnSpringIntegerationTesting.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import org.junit.Assert;

import java.util.List;

//here we are telling spring boot test to test our api's in some random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LearnSpringIntegerationTestingApplicationTests {

	//here we are storing our random port number
	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestH2Repository h2Repository;

	@BeforeAll
	public static void init()
	{
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp()
	{
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/home");
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddUser()
	{
		User user = new User(3,"Mayank", 400000);
		User response = restTemplate.postForObject(baseUrl+"/addUser", user, User.class);
		Assert.assertEquals("Mayank", response.getName());
		Assert.assertEquals(1, h2Repository.findAll().size());
	}

	@Test
	@Sql(statements = "INSERT INTO User_Table(id, name, salary) VALUES(2, 'Ramesh', 50000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM User_Table WHERE name = 'Ramesh'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetAllUser()
	{
		List<User> userList = restTemplate.getForObject(baseUrl+"/" ,List.class);
		Assert.assertEquals(1, userList.size());
		Assert.assertEquals(1, h2Repository.findAll().size());
	}


	@Test
	@Sql(statements = "INSERT INTO User_Table(id, name, salary) VALUES(3, 'Mahesh', 50000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM User_Table WHERE name = 'Mahesh'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUser()
	{
		User user = restTemplate.getForObject(baseUrl+"/{id}",User.class, 3);
		Assert.assertNotNull(user);
		Assert.assertEquals(3, user.getId());
		Assert.assertEquals("Mahesh", user.getName());
	}

	@Test
	@Sql(statements = "INSERT INTO User_Table(id, name, salary) VALUES(4, 'Suresh', 999)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM User_Table WHERE name = 'Suresh'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateUser()
	{
		User user = new User(4, "Suresh", 1999);
		restTemplate.put(baseUrl+"/user/{id}", user, 4);
		User user1 = h2Repository.findById(4).get();
		Assert.assertNotNull(user1);
		Assert.assertEquals(1999, user1.getSalary());
	}

	@Test
	@Sql(statements = "INSERT INTO User_Table(id, name, salary) VALUES(8, 'Kamlesh', 999)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteUser()
	{
		int count = h2Repository.findAll().size();
		Assert.assertEquals(1, count);
		restTemplate.delete(baseUrl+"/delete/{id}", 8);
		Assert.assertEquals(0, h2Repository.findAll().size());
	}





}
