package com.login.run;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", // Path to your feature files
    glue = {"com.login.steps"},                // Package where step definitions are located
    plugin = {"pretty", "html:target/cucumber-reports"}  // Plugins for reporting
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
