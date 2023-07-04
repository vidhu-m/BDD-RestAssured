package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetApiTest {
   
    String getEmp;
   
    @Given("^the valid endpoint to fetch employees$")
    public void setupEndpoint()
    {
    	
    	RestAssured.baseURI="https://dummy.restapiexample.com/";	
    }
   
   
    @When("^the request is send to server$")
    public void sendRequest()
    {
   getEmp = given().log().all().when()
			 .get("api/v1/employees/").then().assertThat().log().all().statusCode(200)
			 .body("status", equalTo("success")) .extract().asString();
    }
   
   
    @Then("^validate the response of first employee record having name as Tiger Nixon$")
    public void validateUserData() throws Exception
    {
    	JsonPath js= new JsonPath(getEmp); //for parsing json
		String empName=js.getString("data.employee_name[0]");
		System.out.println(empName);
		Assert.assertEquals(empName, "Tiger Nixon");  
		
		Thread.sleep(110000);
    }

}