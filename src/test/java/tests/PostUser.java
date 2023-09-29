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
public class PostUser extends BaseClass {
	
	static String user_id = "";
	
	
	@BeforeTest
	public void beforeTest() {

		logger.info("it is before test method");

	}


	@Test(priority = 1)
	public void createUser() {

		Response response = given().auth().basic(secret_key, "")
				.param("name", "Hamidullah Sarem")
				.param("email", "sarem@toptechschool.us")
				.param("description", "sdet")
				.when().post(BaseClass.postUser());
			

		response.prettyPrint();
	//	System.out.printresponse.statusCode());
		logger.info("the status code is  : " + response.getStatusCode());
		logger.info("the new user id is : " + user_id);
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	
	@Test (priority =2)
	public void getSingleUser() {
		Response response = given().auth().basic(secret_key, "")
								   .get(BaseClass.getUsers() + user_id );
		
		

		logger.info("get all users -api");

		String responseBody = response.asString();
		logger.info("validating response body" + responseBody);
		logger.info("validating status code"+ response.statusCode());
		//Assert.assertTrue(false);
	}
	
	
	
	@Test(priority = 3)
	public void ValidateJsonResponse() {
		Response response = given().auth().basic(secret_key, "")
								   .get(BaseClass.getUsers() + user_id );
		
		logger.info("retreiving single user is : "  +  user_id);
		
		JsonPath json =response.jsonPath();
		// 1: 
		logger.info("json path id is" + json.get("id"));
		logger.info("json path name is " + json.get("name"));
		logger.info("the user id is for " + json.get("name"));
		
		
		// 2:
		logger.info(response.jsonPath().getString("address.line1"));
		logger.info(response.jsonPath().getString("preferred_locales[0]"));
		logger.info(response.jsonPath().getMap("$").size());
		logger.info(response.jsonPath().getMap("address").size());
}
}