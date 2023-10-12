package tests;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import core.BaseClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



@Listeners(utils.Listeners.class)
public class DeleteUser extends BaseClass {
	
	static String user_id = "/cus_OjJQVwhSRVakQZ";
	
	
	public void createUser() {

		Response response = given().auth().basic(secret_key, "")
				.param("name", "Maiwand")
				.param("email", "maiwand@toptechschool.us")
				.param("description", "sdet")
				.when().post(BaseClass.postUser());
		
		response.prettyPrint();
	}

	@Test
	public void deletUser() {

		Response response = given().auth().basic(secret_key, "")
				.when().delete(BaseClass.deleteUser() + user_id);
			

		response.prettyPrint();
		logger.info("the status code is  : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("end of delete method");
	}
	
	
	
	public void getSingleUser() {
		Response response = given().auth().basic(secret_key, "")
								   .get(BaseClass.getUsers() + user_id );
		
		
		response.prettyPrint();
		logger.info("get  user -api");

		String responseBody = response.asString();
		logger.info("validating response body" + responseBody);
		logger.info("validating status code"+ response.statusCode());
		
		logger.info("end of getsingleuser method");
	}
	
	
	

	public void ValidateJsonResponse() {
		Response response = given().auth().basic(secret_key, "")
								   .get(BaseClass.getUsers() + user_id );
		
		logger.info("retreiving single user is : "  +  user_id);
		
		JsonPath json =response.jsonPath();
		// 1: 
		logger.info("json path id is" + json.get("id"));
		logger.info("json path name is " + json.get("name"));
		logger.info("the user id is for " + json.get("name"));
		
		
		logger.info("end of validate method");
	
	
}
}