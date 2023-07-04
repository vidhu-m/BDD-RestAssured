package testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



/*@CucumberOptions(
	features = {"classpath:features/feature.feature"},
    glue = {"stepDefinitions"},
    tags = "@RestAPITest",
    plugin = { "pretty", "json:target/cucumber-reports/CucumberTestReport.json","html:target/cucumber-reports/cucumberreport.html" }
    )*/

@CucumberOptions(tags= "@RestAPITest",
glue = "stepDefinitions",
features="src/test/java/FeatureFile")
public class Runner extends AbstractTestNGCucumberTests{
}