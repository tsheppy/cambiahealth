package gradle.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        tags = {"~@notCircleCiCompatible"})
public class RunCukesTest {

}