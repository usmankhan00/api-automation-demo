package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * This is runner file where we can run the project.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/",
        glue = "stepDefinition",
        //plugin = { "pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@Smoke"
)

public class TestRunner {

}
