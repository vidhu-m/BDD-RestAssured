package apiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class ReadEmp {
	
  
	@Test
	public void getAllEmpDetails()
	{
		given().when().get("https://dummy.restapiexample.com/api/v1/employees").then().log().all()
		.assertThat().statusCode(200).header("Content-Type","application/json");
	}
	
	
}
