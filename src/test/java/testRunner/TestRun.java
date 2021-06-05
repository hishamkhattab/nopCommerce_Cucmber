package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "D:\\WorkSpace\\nopCommerceV001_Cucumber\\src\\test\\resources\\features",
        //dryRun = true,
        glue = "stepDefinitions",
        plugin = {"pretty", "html:test-output"}
)
public class TestRun {
}
