package api.test;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.Routes;
import api.endpoints.UserEndPoinds;
import api.payload.UserDetails;
import api.payload.UserList;
import api.payload.User_Input;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	String userId;
	User_Input userPayload = new User_Input();

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String name, String job) {
		//User_Input userPayload = new User_Input();

		userPayload.setName(name);
		userPayload.setJob(job);

		Response response = UserEndPoinds.createUser(userPayload);
		response.then().log().all();
		
		userId = response.jsonPath().getString("id");
		System.out.println(userId);

		Assert.assertEquals(response.getStatusCode(), 201); // Or 201 if it's a create endpoint
	}

	//@Test(priority = 2, dataProvider = "UserIDs", dataProviderClass = DataProviders.class)
	public void testDeleteUserById(String userid) {
		 Response response = given()
	                .header("x-api-key", "reqres-free-v1")
	                .pathParam("id", userId)
	        .when()
	                .delete(Routes.delete_URL); // Directly calling endpoint

	        System.out.println("Deleted ID: " + userId + " | Status Code: " + response.getStatusCode());
	        Assert.assertEquals(response.getStatusCode(), 204);
	}
}