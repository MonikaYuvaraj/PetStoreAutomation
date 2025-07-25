package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoinds;
import api.payload.User_Input;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User_Input userpayload;
	String userId;
	
	public Logger logger;
	@BeforeClass
	private void setup() {

		faker = new Faker();
		userpayload = new User_Input();

		userpayload.setName(faker.name().firstName());
		userpayload.setJob(faker.job().title());

//		userpayload.setName("Monika");
//		userpayload.setJob("monika");
		
		logger = LogManager.getLogger(this.getClass()) ;
		logger.info("Setting up data for User API tests");

	}

	@Test(priority = 1)
	public void testPostUser() {

		logger.info("**********Creating a new user****************");
		
		Response response = UserEndPoinds.createUser(userpayload);
		response.then().log().all();

		// Assert.assertEquals(response.getStatusCode(), 201);
		// Extract and store ID from response
		userId = response.jsonPath().getString("id");
		System.out.println(userId);
		
		logger.info("****************User created***************");
	}

	@Test(priority = 2)
	public void testGetUser() {

		logger.info("**********Reading user details****************");
		Response response = UserEndPoinds.readUser(2);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
//		Assert.assertEquals(response.getStatusCode(), 201);
//		Assert.assertNotNull(response.jsonPath().get("id"));
//		Assert.assertEquals(response.jsonPath().get("name"), "Monika");
		logger.info("****************User details read***************");
		
	}
	

	@Test(priority = 3)
	public void testUpdateUser() {

		logger.info("**********Updating user details****************");
		userpayload.setName(faker.name().fullName());
		userpayload.setJob("Senior Tester");

		Response response = UserEndPoinds.updateUser(2, userpayload);
		// Static ID as reqres doesn't persist
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("job"), "Senior Tester");
		
		logger.info("****************User details updated***************");

	}

	@Test(priority = 4)
	public void testDeleteUser() {

		logger.info("**********Deleting user****************");
		Response response = UserEndPoinds.deleteUser(2);
		// Static ID as reqres doesn't persist
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
		
		logger.info("****************User deleted***************");

	}
	
	
}
