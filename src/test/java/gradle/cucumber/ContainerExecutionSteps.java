package gradle.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainerExecutionSteps {
    private Map<String, String> inputOutputFileMap = new HashMap<>();
    private String workingDir = Paths.get(System.getProperty("dockerWorkingDir")).toAbsolutePath().toString();
    private final String dockerImage = System.getProperty("dockerImage");
    private int actualExitValue = 0;

    @Given("^The following input files and matching expected output files$")
    public void theFollowingInputFilesAndMatchingExpectedOutputFiles(Map<String, String> files) {
        inputOutputFileMap = files;
    }

    @When("^I invoke the CSV Parser container$")
    public void iInvokeTheCSVParserContainer() throws Throwable {
        String dockerCommand = "docker run --rm "
                + "-v " + workingDir + ":/work" + " "
                + dockerImage;
        System.out.println(dockerCommand);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(dockerCommand);
        process.waitFor();
        actualExitValue = process.exitValue();
    }

    @Then("^The expected output files match the actual output files$")
    public void theExpectedOutputFilesMatchTheActualOutputFiles() throws Throwable {
        Assert.assertEquals("The process exited with code " + actualExitValue + " instead of 0 as expected", 0, actualExitValue);

        for (String inputFileName : inputOutputFileMap.keySet()) {

            Path expectedOutputPath = Paths.get(workingDir + File.separator + inputOutputFileMap.get(inputFileName));
            Path actualOutputPath = Paths.get(workingDir + File.separator + "output.csv");

            List<String> actualOutput = Files.readAllLines(actualOutputPath);
            List<String> expectedOutput = Files.readAllLines(expectedOutputPath);

            Assert.assertTrue(expectedOutput.equals(actualOutput));
        }
    }

    @Then("^The error code returned is (\\d+)$")
    public void theErrorCodeReturnedIs(int expectedExitCode) {
        Assert.assertEquals(expectedExitCode, actualExitValue);
    }

    @Given("^An input file is not present$")
    public void anInputFileIsNotPresent() {
        workingDir = Paths.get(workingDir).getParent().toAbsolutePath().toString();
    }
}
