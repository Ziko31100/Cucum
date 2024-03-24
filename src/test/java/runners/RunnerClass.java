package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "steps",
      dryRun = false,
   //     tags = "@sprint1 or @sprint2"
tags = "@dbTest",
plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json",
        // rerun plugin generates the txt file of all failed test scenarios
        "rerun:target/failed.txt"}

)
public class RunnerClass {

}
