package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.UserDetails;
import api.payload.UserList;
import api.payload.User_Input;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoinds2ByPropertyFile {

	
	 static ResourceBundle getUrl(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	// Base URL for the API
	public static Response createUser(User_Input payload) {
		String postUrl = getUrl().getString("user_post");
		Response response = given()
		        .header("x-api-key", "reqres-free-v1")  // Add this line
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(postUrl);
		return response;
	}

	
	public static Response readUser(int id) {
		Response response = given()
				.header("x-api-key", "reqres-free-v1")
				.pathParam("id", id)
			.when()
				.get(Routes.get_URL);
		return response;
	}

	public static Response updateUser(int id,User_Input payload) {
		Response response = given()
				.header("x-api-key", "reqres-free-v1")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("id", id)
			.when()
				.put(Routes.update_URL);
		return response;
	}

	public static Response deleteUser(int id) {
		Response response = given()
				.header("x-api-key", "reqres-free-v1")
				.pathParam("id", id)
			.when()
				.delete(Routes.delete_URL);
		return response;
	}

	
	
}
