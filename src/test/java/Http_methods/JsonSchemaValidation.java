package Http_methods;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
public class JsonSchemaValidation {

	
	 public static String secret_key = "sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	 public static String secret_key2 = "Bearer sk_test_51MnoCzCG0XLhk2w9oaMABmQRLgcEsLaW6XrjdHCtRTwbxXB7mz55R1TZs8khqiykx6HzrPzlR2c5ap9J3qeShh5D009eT0AHpN";
	
	 
	 @Test
	 public void jsonSchemavalidation() throws FileNotFoundException {
		
		 FileInputStream jsonShema = new FileInputStream("C:\\Users\\T540p\\eclipse-workspace\\RestAssured-API-Automation-Testing\\src\\test\\resources\\test-data\\jsonSchema.json");
		 RestAssured.baseURI = "https://api.stripe.com";

		    //GET operation
		  given().auth().basic(secret_key, "").
		    when().post("/v1/customers/cus_OnNtYWKE44VDjR").
		    then().assertThat().body (JsonSchemaValidator.matchesJsonSchema (jsonShema))
			.statusCode(200);

	
	
}
}