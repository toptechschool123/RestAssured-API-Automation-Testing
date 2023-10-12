package Http_methods;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ExtractFieldValueFromResponse {

	public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";


	@Test
	public static void JsonResponseValidation() {

		Response response = given().auth().basic(secret_key, "").
				 param("name", "Liz")
				.param("email", "trainer@toptechschool.us")
				 .param("address[line1]", "Seattle WA")
				 .param("preferred_locales[0]","No")
				.post("https://api.stripe.com/v1/customers ");

		response.prettyPrint();
		
		// two ways: 
		// 1: 
		JsonPath json =response.jsonPath();
		System.out.println("the json path id is " + json.get("id"));
		System.out.println("the json path name is " + json.get("name"));
		
		// 2:
		System.out.println(response.jsonPath().getString("address.line1"));
		System.out.println(response.jsonPath().getString("preferred_locales[0]"));
		
		System.out.println(response.jsonPath().getMap("$").size());
		System.out.println(response.jsonPath().getMap("address").size());
	}
	
	@Ignore
	@Test
	public void getAllUsers() {
		
	
		Response response =	given().auth().basic(secret_key, "").
				get("https://api.stripe.com/v1/customers");
		
		response.prettyPrint();
	}
}
