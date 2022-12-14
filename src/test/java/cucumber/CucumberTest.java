package cucumber;

import io.cucumber.testng.*;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue = {"cucumber", "runner"},
        plugin = {"pretty", "html:/target/cucumber-reports/cucumber.html"},
        tags = "not @ignore")

public class CucumberTest extends AbstractTestNGCucumberTests {
}
