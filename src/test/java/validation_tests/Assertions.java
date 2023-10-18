package validation_tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Assertions {
	public static String secret_key2 = "Bearer sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	public static String user_id = "cus_Oq2N9wNEnarZwY";
	public static String user_name = "Liz2";
	static String email= "trainer2@toptechschool.us";
	
	
	@Test
	public void assertions() {
		
		Response response = given().auth().basic(secret_key, "")
				
				.get("https://api.stripe.com/v1/customers/"+user_id);

	
		System.out.println("headers are " + response.headers());
		System.out.println("cookies are " + response.cookies());
		System.out.println("time is " + response.getTime());
	
		
	 	// Retrieve the body of the Response
	 	ResponseBody body = response.getBody();

	 	// By using the ResponseBody.asString() method, we can convert the  body
	 	// into the string representation.
	 	System.out.println("Response Body is: " + body.asString());
	 	
	
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.contentType(), "application/json");
		Assert.assertEquals(response.header("Vary"), "Origin");
		
		
		Assert.assertEquals(response.jsonPath().getString("email"), email);
		Assert.assertEquals(response.jsonPath().getString("name"), user_name);
		
		Assert.assertEquals(response.jsonPath().getString("address.line1"), "Seattle WA");
		Assert.assertEquals(response.jsonPath().getString("address.postal_code"), null);
		
		Assert.assertEquals(response.jsonPath().getString("invoice_prefix"), "20A029F9");
		
		Assert.assertEquals(response.jsonPath().getInt("created"),1697583816);
	
		
		 // To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String jsonResponse = body.asString();
		Assert.assertEquals(jsonResponse.contains("Liz2"),true);
		Assert.assertEquals(jsonResponse.contains("city"),false);
	}
}
