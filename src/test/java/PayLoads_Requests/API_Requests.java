package PayLoads_Requests;

import static io.restassured.RestAssured.given;
import core.BaseClass;
import io.restassured.response.Response;

public class API_Requests extends BaseClass {

	 public static String user_id = "/cus_OgZimuZ3pnxx9q";

	public static Response  getAllUsers() {
		
		Response response = given().auth().basic(secret_key, "")
				.get(BaseClass.getUsers());

		String responseBody = response.asString();
		logger.info("validating response body" + responseBody);
		logger.info("validating status code" + response.statusCode());
		return response;

	}

	public static Response getSingleUser() {
		Response response = given().auth().basic(secret_key, "")
				.get(BaseClass.getUsers() + user_id);

	//	String responseBody = response.asString();
		//logger.info("response body is :" + responseBody);
		//logger.info("validating status code is : " + response.statusCode());
		return response;

	}

	public static void createUser() {

		Response response = given().auth().basic(secret_key, "")
				.param("name", "Mayar")
				.param("email", "mayar@toptechschool.us")
				.param("description", "sdet")
				.when().post(BaseClass.postUser());

		response.prettyPrint();
	}

	public static void deletUser() {

		Response response = given().auth().basic(secret_key, "")
				.when().delete(BaseClass.deleteUser() + user_id);

		response.prettyPrint();
		logger.info("the status code is  : " + response.getStatusCode());
	
	
	}

}
