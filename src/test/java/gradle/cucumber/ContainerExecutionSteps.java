package gradle.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Before;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainerExecutionSteps {
    private Map<String, String> inputOutputFileMap;
    private final Path outputDir = Paths.get("build/tmp/");
    private final Path samplesDir = Paths.get("src/test/resources/samples/");
    private final String dockerImage = System.getProperty("dockerImage");
    private int exitCode = 0;

    @Before
    public void resetState() {
        exitCode = 0;
        inputOutputFileMap = new HashMap<>();
    }

    @Given("^The following input files and matching expected output files$")
    public void theFollowingInputFilesAndMatchingExpectedOutputFiles(Map<String, String> files) throws Throwable {
        inputOutputFileMap = files;
    }

    @When("^I invoke the CSV Parser container$")
    public void iInvokeTheCSVParserContainer() throws Throwable {
        for (String inputFile : inputOutputFileMap.keySet()) {
            Runtime runtime = Runtime.getRuntime();
            String dockerCommand = "docker run --rm "
                    + "-v " + samplesDir.toAbsolutePath() + ":/input" + " "
                    + "-v "  + outputDir.toAbsolutePath() + ":/output" + " "
                    + "-e INPUT_FILE=" + inputFile + " "
                    + dockerImage;
            System.out.println(dockerCommand);
            Process process = runtime.exec(dockerCommand);
            process.waitFor();
            exitCode = process.exitValue();
        }
    }

    @Then("^The expected output files match the actual output files$")
    public void theExpectedOutputFilesMatchTheActualOutputFiles() throws Throwable {
        Assert.assertTrue("The process exited with code " + exitCode + " instead of 0 as expected", exitCode == 0);
        for (String inputFile : inputOutputFileMap.keySet()) {
            Path fileOutputPath = Paths.get(outputDir + inputFile);
            List<String> actualOutput = Files.readAllLines(fileOutputPath);

            Path expectedOutputPath = Paths.get(samplesDir + inputOutputFileMap.get(inputFile));
            List<String> expectedOutput = Files.readAllLines(expectedOutputPath);

            Assert.assertTrue(expectedOutput.equals(actualOutput));
        }
    }
}