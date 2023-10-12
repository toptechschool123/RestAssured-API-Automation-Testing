package Http_methods;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.response.Response;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;
public class Get_Put_Requests {
	
	/*
	 * Set authentication --> basic auth/bearer token using headers
	 * Request -- > set of request specifications
	 * 
	 * using Contenttype() method
	 * adding the contentType in the headers
	 *  1- given().contentType(ContentType.JSON);
	 *  2- given().contentType("application/json");
	 *  3- given().header("content-type", "application/json").auth().basic...
	 * 
	 * Response --> Do validation
	 */
 public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
 public static String secret_key2 = "Bearer sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";

 	@Ignore
	@Test
	public void getUsers() {
		Response response =	given().auth().basic(secret_key, "").get("https://api.stripe.com/v1/customers");
		
		// we can store response int different ways: 
		// 1: 
		 //   response.prettyPrint();
		
		// 2: we can also store the response in a string :
		  String responseBody =response.asString();
		System.out.println(responseBody);
		
		System.out.println("response code is " + response.statusCode());
	}
	
	
	@Ignore
	@Test
	public void singleUser() {
		
	Response response=	given().header("Authorization",secret_key2 ).get("https://api.stripe.com/v1/customers/cus_OkRYiD5bD2ctXz");
	
	response.prettyPeek();
	
	}
	@Ignore
	 @Test
		public void createUser1() {
		 
			 
			Response response = given().auth().basic(secret_key, "")
					.param("name", "Shaker")
					.param("email", "Shaker@toptechschool.us")
					.param("description", "sdet")
					.when().post("https://api.stripe.com/v1/customers");
				
			response.prettyPeek();
	 }
	 
	
	@Test
	public void updateUser() {
	Response resposne =	given().header("Authorization",secret_key2 ).
			param("name", "Shaikh").
			post("https://api.stripe.com/v1/customers/cus_On7ve5yUi6lgv7");
	
		
	resposne.prettyPrint();
		// we can store response int different ways: 
		// 1: 
		 //   response.prettyPrint();
		

}
}