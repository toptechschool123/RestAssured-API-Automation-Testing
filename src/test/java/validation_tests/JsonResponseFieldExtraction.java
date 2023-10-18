package validation_tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonResponseFieldExtraction {
	
	public static String secret_key2 = "Bearer sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	public static String user_id = "cus_Oq2N9wNEnarZwY";
	public static String user_name = "Liz2";
	static String email= "trainer2@toptechschool.us";
	@Ignore
	@Test
	public static void jsonResponseFieldExtraction() {

		Response response = given().auth().basic(secret_key, "").
				 param("name", "Liz2")
				.param("email", "trainer2@toptechschool.us")
				 .param("address[line1]", "Seattle WA")
				 .param("preferred_locales[0]","No")
				.post("https://api.stripe.com/v1/customers ");

		response.prettyPrint();
		
		// JsonPath class: used to retrieve data from a JSON document and to verify json fields
		// 1: 
		JsonPath json =response.jsonPath();
		System.out.println("the json path id is " + json.get("id"));
		System.out.println("the json path name is " + json.get("name"));
		
		System.out.println("the json path address is " + json.get("address.line1"));
		System.out.println("the json path for livemode is " + json.get("livemode"));
		// 2:
		System.out.println(response.jsonPath().getString("address.line1"));
		System.out.println(response.jsonPath().getString("preferred_locales[0]"));
		
		System.out.println(response.jsonPath().getMap("$").size());
		System.out.println(response.jsonPath().getMap("address").size());
	}
	
	

}
