package gradle.cucumber;

import com.example.CsvSorter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Before;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FileWritingSteps {
    private static Path outputPath;
    private static final String defaultOutputPath = "build/tmp/default.csv";
    private static Exception caughtException;

    @Before
    public void resetState() {
        outputPath = null;
        caughtException = null;
    }

    @Given("^I provide a valid file output path$")
    public void Provide_valid_file_output_path() {
        outputPath = Paths.get(defaultOutputPath);
    }

    @When("^I write some entries to a file$")
    public void Write_Some_Entries_To_File() {
        try {
            CsvSorter.writeEntriesToFile(Arrays.asList("a,b,c,d"), outputPath);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Then("^A file is created at the provided path$")
    public void file_Created() {
        File expectedFile = outputPath.toFile();
        Assert.assertTrue("File not created at " + outputPath, expectedFile.exists());
    }

    @Given("^I provide an invalid file output path$")
    public void iProvideAnInvalidFileOutputPath(){
        outputPath = null;
    }

    @Then("^An exception is thrown when writing to file$")
    public void exceptionThrownWhenWritingFile() {
        Assert.assertNotNull("Exception not thrown as expected", caughtException);
    }
}