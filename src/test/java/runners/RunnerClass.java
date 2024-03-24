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
tags = "@datatable",
plugin = {"pretty"}

)
public class RunnerClass {

}
