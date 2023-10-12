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
public class Assertions extends BaseClass {
	
	 static String user_id = "cus_OgZimuZ3pnxx9q";
	 static String user_name = "Sayed";
	
	
	 @Test
	 public void getSingleUser() {
			Response response = given().auth().basic(secret_key, "")
									   .get(BaseClass.getUsers() + "/"+user_id );
			
			
			response.prettyPrint();
			System.out.println("user id is " +response.jsonPath().get("id").toString());
			System.out.println("user name is " + response.jsonPath().get("name").toString());
			
			
			
		String actual_id =	response.jsonPath().get("id");
		String actual_name =	response.jsonPath().get("name");
		
		String actual_State =response.jsonPath().get("state");
		
		
		Assert.assertEquals(actual_id, user_id , "id not matched");
		Assert.assertEquals( actual_name, user_name , "nmae not matched" );
		
		Assert.assertEquals(response.statusCode(), 200);
		
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.contentType(), "application/json");
		
		
		String atual_addresslIne = "3445 S 144th St";
		String postalCode = "98168";
		Assert.assertEquals(response.jsonPath().getString("address.line1"), atual_addresslIne);
		
		Assert.assertEquals(response.jsonPath().getString("address.postal_code"), postalCode);
	 }
	 
	 
	 
	@Ignore
    @Test
	public void getAllUsers() {
		Response response = given().auth().basic(secret_key, "")
								   .get(BaseClass.getUsers());
	
		String responseBody = response.asString();
		
		logger.info(responseBody);
	
	
		
	}
	
	

	public void ValidateJsonResponse() {
		Response response = given().auth().basic(secret_key, "")
								   .get(BaseClass.getUsers() + user_id );
		
		logger.info("user id is : "  +  user_id);
		
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