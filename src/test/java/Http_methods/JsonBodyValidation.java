package Http_methods;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
public class JsonBodyValidation {
	 public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	 public static String secret_key2 = "Bearer sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	public static String user_id = "cus_OnNtYWKE44VDjR";
	public static String user_name = "Liz";
	 @Ignore
	 @Test
	public static void jsonBodyValidation() {
		
	
	//base URL
    RestAssured.baseURI = "https://api.stripe.com";

    //GET operation
    given().auth().basic(secret_key, "").
    when().get("/v1/customers/" + user_id).
    then().log().all()

    //verify status code as 200
    .assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK")

    //verify body
    .body("name", Matchers.equalTo("Liz"),"created",Matchers.equalTo(1696971950))

    //verify header
    .headers("Connection" , "keep-alive","Server","nginx" );
}
	 
	 @Test
	 public static void jsonBodyValidation2() {
			
	
		 	RestAssured.baseURI = "https://api.stripe.com";
		 	RequestSpecification httpRequest = RestAssured.given().auth().basic(secret_key, "");
		 	Response response = httpRequest.get("/v1/customers/" + user_id);

		 	// Retrieve the body of the Response
		 	ResponseBody body = response.getBody();

		 	// By using the ResponseBody.asString() method, we can convert the  body
		 	// into the string representation.
		 	System.out.println("Response Body is: " + body.asString());
		 	
		 // To check for sub string presence get the Response body as a String.
			// Do a String.contains
			String bodyAsString = body.asString();
			Assert.assertEquals(bodyAsString.contains("Liz"),true);
		 	
			
			
			String actual_id =	response.jsonPath().get("id");
			String actual_name =	response.jsonPath().get("name");
			
			String actual_State =response.jsonPath().get("state");
			
			
			Assert.assertEquals(actual_id, user_id , "id not matched");
			Assert.assertEquals( actual_name, user_name , "name not matched" );
			
			Assert.assertEquals(response.statusCode(), 200);
			
			Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
			Assert.assertEquals(response.contentType(), "application/json");
			
			
			String atual_addresslIne = "Seattle WA";
			String postalCode = null;
			
			Assert.assertEquals(response.jsonPath().getString("address.line1"), atual_addresslIne);
			
			Assert.assertEquals(response.jsonPath().getString("address.postal_code"), postalCode);
		 	
		 }
			
}