package gradle.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "junit:build/reports/cucumber.xml"},
        tags = {"not @NotCircleCiCompatible"})
public class RunCukesTest {

}