package Http_methods_school;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Http_Request {
	
	public static String user_id = "5";
	
	
	@Test
	public void getUsers() {
		
	Response response =	given().get("https://testapi.toptechschool.us/api/get-users");
		
	response.prettyPrint();
	

	String header_date =response.header("date");
	System.out.println( " date is "  + header_date);
	
	Headers allHeaders = response.getHeaders();
	  
	System.out.println( "all headers are " +  allHeaders);
	  
	String contenType = response.getContentType();
	System.out.println("contentType contains " +  contenType);
	 
	int statusCode=response.getStatusCode(); 
	System.out.println(statusCode);
	
	JsonPath json =response.jsonPath();
	System.out.println("the json path id is " + json.get("id"));
	System.out.println("the json path name is " + json.get("name"));
	}
	@Ignore
	@Test
	public void postUser() {
		
		Response response =	given().
				param("first_name", "Lara").
				param("last_name"," Larr").
				param("username", "larr123").
				param("email", "larr@yahoo.com").
				param("phone_number", "555333555").
				param("address", "FL").
				post("https://testapi.toptechschool.us/api/register");
		
		response.prettyPeek();
		
		
	}
	
	
	@Test
	public void getSingleUser() {
		Response response =	given().get("https://testapi.toptechschool.us/api/get-user");
		//int id = response.jsonPath().get("id");
		response.prettyPrint();
		

		String header_date =response.header("date");
		System.out.println( " date is "  + header_date);
		
		Headers allHeaders = response.getHeaders();
		  
		System.out.println( "all headers are " +  allHeaders);
		  
		String contenType = response.getContentType();
		System.out.println("contentType contains " +  contenType);
		 
		int statusCode=response.getStatusCode(); 
		System.out.println(statusCode);
		
		JsonPath json =response.jsonPath();
		System.out.println("the json path id is " + json.get("id"));
		System.out.println("the json path name is " + json.get("name"));
	}

}
