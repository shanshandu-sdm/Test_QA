package TestRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features/mySFBULogin1.feature",glue={"StepDefinition"})
public class JUnitRunner
{

}