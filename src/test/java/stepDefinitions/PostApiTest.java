package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PostApiTest {
   
String response;
String id;
   
    @Given("^the valid endpoint with payload to create employee$")
    public void setupEndpointAndPostData()
    {
    	RestAssured.baseURI="https://dummy.restapiexample.com/";		

    }
   
   
    @When("^the request is send to the server$")
    public void sendRequest()
    {
    	response= 
				 given().log().all().header("Content-Type", "application/json")
		.body(Payload.Employee()).when().post("api/v1/create").then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
    	JsonPath js= new JsonPath(response); //for parsing json
		String empName=js.getString("data.name");
		id=js.getString("data.id");
		//System.out.println(empName);
    }
   
    @And("^the new employee must be created with name as test$")
    public void validateResponse()
    {
    	JsonPath js= new JsonPath(response); //for parsing json
		String empName=js.getString("data.name");
		//System.out.println(empName);
		Assert.assertEquals(empName, "test");  
    }
   
    @And("^the new employee must be created must be fetched$")
    public void fetchEmployee() throws Exception
    {
    	Thread.sleep(120000);
    	 given().log().all().when()
		 .get("api/v1/employee/"+id).then().assertThat().log().all().statusCode(200)
		 .body("status", equalTo("success")) .extract().asString();
    }
    
    @And("^the new employee created must be updated$")
    public void updateEmployee() throws Exception
   {
   	Thread.sleep(120000);
  	 given().log().all().header("Content-Type", "application/json")
			.body("{\"name\":\"test_update\",\"salary\":\"555\",\"age\":\"43\"}").when()
			.put("api/v1/update/"+id).then().log().all().statusCode(200)
		.body("status", equalTo("success"));
    }
    
    @Then("^the new employee created must be deleted$")
    public void deleteEmployee() throws Exception
   {

		Thread.sleep(120000);
		given().log().all().when().delete("api/v1/delete/"+id)
	    .then().assertThat().log().all().statusCode(200);

    }
    

}
