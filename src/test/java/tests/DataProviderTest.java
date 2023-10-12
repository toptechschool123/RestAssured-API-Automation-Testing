package tests;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import core.BaseClass;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ExcelUtility;



@Listeners(utils.Listeners.class)
public class DataProviderTest extends BaseClass {
	
	static String user_id = "cus_OgZimuZ3pnxx9q";
	
	
	@BeforeTest
	public void beforeTest() {

		logger.info("it is before test method");

	}


	@Test(dataProvider = "dataSet")
	public void createUser(String name, String email, String description) {

		Response response = given().auth().basic(secret_key, "")
				.param("name", name)
				.param("email", email)
				.param("description", description)
				.when().post(BaseClass.postUser());
			

		response.prettyPrint();
		logger.info("the status code is  : " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	
	@DataProvider() 
	public Object [][] dataSet () {
		
		Object [][] data = ExcelUtility.getExcelData("info");
		return data;
		
}
}