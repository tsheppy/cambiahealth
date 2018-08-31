package gradle.cucumber;

import com.example.CsvSorter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class ArraySortingSteps {
    private List<String> csv;

    @Given("^I have an entry of \"(.*)\"$")
    public void I_have_CSV_entry(String entry) {
        csv = Arrays.asList(entry);
    }

    @When("^I sort the entry")
    public void I_sort_array() {
        csv = CsvSorter.sortCsvEntries(csv);
    }

    @Then("^I have a result of \"(.*)\"$")
    public void I_have_CSV_result(String expectedResult) {
        String formattedResult = '[' + expectedResult + ']';
        Assert.assertEquals(formattedResult, csv.toString());
    }
}