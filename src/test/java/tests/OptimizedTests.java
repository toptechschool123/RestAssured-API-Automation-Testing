package tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import PayLoads_Requests.API_Requests;
import core.BaseClass;
import io.restassured.response.Response;

public class OptimizedTests extends BaseClass {

	 static String id = "cus_OgZimuZ3pnxx9q";
	 static String name = "Sayed";
	public void getAllUser() {
		

	Response response = API_Requests.getAllUsers();
}
	@Test
	public void getSingleUser() {
		Response response = API_Requests.getSingleUser();
		
			System.out.println(response.jsonPath().get("id").toString());
			System.out.println(response.jsonPath().get("name").toString());
			
			
			
		String actual_id =	response.jsonPath().get("id").toString();
		String actual_name =	response.jsonPath().get("name").toString();
		
		Assert.assertEquals(actual_id, id );
		Assert.assertEquals( actual_name, name  );
	}
}