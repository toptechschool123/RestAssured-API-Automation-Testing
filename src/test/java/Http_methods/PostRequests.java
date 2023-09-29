package Http_methods;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import utils.User;

public class PostRequests {
	 public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	 public static String secret_key2 = "Bearer sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
		
	// we create users in two ways: 1: using parameters 2: using json format. 
		
	 @Ignore
	 @Test
		public void createUser1() {
			Response response =	given().auth().basic(secret_key, "").
					param("name", "Tomy3").
					param("job"," Sdettrainer2").
					param("email", "tomy2@hotmail.com").
					param("country", "Paris,France").
					post("https://api.stripe.com/v1/customers");
			
			response.prettyPeek();
			
			
	 }
		
		 @Ignore
		@Test		// create paylaoad with parameters
		public void createUsers2() {
			Response response =	given().
					param("first_name", "Lisa").
					param("last_name"," Love").
					param("username", "love123").
					param("email", "love@yahoo.com").
					param("phone_number", "555333555").
					param("address", "Austin").
					post("https://testapi.toptechschool.us/api/register");
			
			response.prettyPeek();
			
			
}
		 @Ignore
		@Test            // create payload using Map/HashMap
		public void createUser3()	{
			
			Map <String, Object> map = new HashMap<String, Object>();
		
		map.put("first_name", "jimy");
		map.put("last_name"," jim");
		map.put("username", "jim123");
		map.put("email", "jimi@yahoo.com");
		map.put("phone_number", "55553355");
		map.put("address", "Florida");
		
		Response response = given().contentType(ContentType.JSON).
		body(map).
		post("https://reqres.in/api/users");
		
		response.prettyPrint();
		
		
		} 
		 @Ignore
		@Test
		public void createUser4() { 
			// we can also create payload using JSONOBJECT CLASS 
			
			JSONObject request = new JSONObject();
			request.put("Name", "Liza");
			request.put("Job", "Manual Software Tester");
			request.put("role", "Assistant lead");
			
			ValidatableResponse response1 = given().contentType(ContentType.JSON).
					body(request.toJSONString()).
					when().post("https://reqres.in/api/users").then().statusCode(201).log().all();
			
					
		}
		
		 @Ignore
		@Test   // create payload using json file
		public void createUser5() {
			
			String filePath = ".\\src\\test\\resources\\test-data\\users.json";
			File file = new File(filePath);
			
			ValidatableResponse response1 = given().contentType(ContentType.JSON).
					body(file).
					when().post("https://reqres.in/api/users").then().statusCode(201).log().all();
		
		} 
		
		@Ignore
		@Test    // using JSONOBJECT AND JSON ARRAY CLASSES- complex one
		public void createUser6() {
			
			/* {
			 * 	"name" : "sayed",
			 * 	"email": " sayed@toptechschool.us",
			 * 	"mobile": [22222222, 44444444],
			 *  "address": {
			 *  			"city": "seattle",
			 *  			" state": "WA",
			 *  			" country": "USA"
			 *  
			 *  }
			 */
			
		JSONObject JsonObject = new JSONObject();
		
		JsonObject.put("name","sayed");
		JsonObject.put("email", "sayed@toptechschool.us");
		
		JSONArray listOfNumbers = new JSONArray();
		
		listOfNumbers.add(222222);
		listOfNumbers.add(4444444);
		
		JsonObject.put("numbers",listOfNumbers);
		
		JSONObject address = new JSONObject();
		
		address.put("city", "seattle");
		address.put("state", "WA");
		address.put("country", "USA");
		
		JsonObject.put("adress",address);
		
		Response response = given().contentType(ContentType.JSON).
				body(JsonObject.toJSONString()).
				when().post("https://reqres.in/api/users");
		
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		
		
		}
		
		@Test
		public void CreateUserWithPojo() {
			User user = new User("sayed@toptechschool.us", "sayed", "sadat");
			
			
			Response response = given().contentType(ContentType.JSON).
					body(user).
					when().post("https://reqres.in/api/users");
			
			
			response.prettyPrint();
			System.out.println(response.getStatusCode());
			
			
			
			
			
}}