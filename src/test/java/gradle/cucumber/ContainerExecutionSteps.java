package gradle.cucumber;

import com.example.CsvSorter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Before;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainerExecutionSteps {
    private Map<String, String> inputOutputFileMap;
    private final Path workingDir = Paths.get(System.getProperty("dockerWorkingDir"));
    private final String dockerImage = System.getProperty("dockerImage");
    private int actualExitValue = 0;

    @Before
    public void resetState() {
        actualExitValue = 0;
        inputOutputFileMap = new HashMap<>();
    }

    @Given("^The following input files and matching expected output files$")
    public void theFollowingInputFilesAndMatchingExpectedOutputFiles(Map<String, String> files) {
        inputOutputFileMap = files;
    }

    @When("^I invoke the CSV Parser container$")
    public void iInvokeTheCSVParserContainer() throws Throwable {
        for (String inputFile : inputOutputFileMap.keySet()) {
            String dockerCommand = "docker run --rm "
                    + "-v " + workingDir.toAbsolutePath() + ":/work" + " "
                    + "-e CSV_INPUT_FILE=" + inputFile + " "
                    + dockerImage;
            System.out.println(dockerCommand);

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(dockerCommand);
            process.waitFor();
            actualExitValue = process.exitValue();
        }
    }

    @Then("^The expected output files match the actual output files$")
    public void theExpectedOutputFilesMatchTheActualOutputFiles() throws Throwable {
        Assert.assertTrue("The process exited with code " + actualExitValue + " instead of 0 as expected", actualExitValue == 0);

        for (String inputFileName : inputOutputFileMap.keySet()) {
            System.out.println("inputFileName = " + inputFileName);
            System.out.println("outputFileName = " + inputOutputFileMap.get(inputFileName));

            Path expectedOutputPath = Paths.get(workingDir + File.separator + inputOutputFileMap.get(inputFileName));
            Path actualOutputPath = CsvSorter.formatOutputFilePath(Paths.get(workingDir + File.separator + inputFileName));

            List<String> actualOutput = Files.readAllLines(actualOutputPath);
            List<String> expectedOutput = Files.readAllLines(expectedOutputPath);

            Assert.assertTrue(expectedOutput.equals(actualOutput));
        }
    }

    @Then("^The error code returned is (\\d+)$")
    public void theErrorCodeReturnedIs(int expectedExitCode) {
        Assert.assertEquals(expectedExitCode, actualExitValue);
    }
}
