package testSteps.dateParser;

import io.cucumber.java8.En;
import org.testng.Assert;
import pageobjects.DateParser;

/**
 * Step defenition class fro date parser
 */
public class ValidateDateParser implements En {
    private DateParser dtpObj = new DateParser();

    public ValidateDateParser(){

        //Validate if the form available or not
        Given("Validate is form available to fill", () -> {
            Assert.assertEquals(dtpObj.isFormAvailable(), true, "Form is available in the page");
        });

        //Add value to date field
        When("Add {string} date to text field", (String dateVal) -> {
            Assert.assertEquals(dtpObj.userInsertDateField(dateVal), dateVal, "User insert Date");
        });

        // click on submit button
        When("Click submit button", () -> {
            Assert.assertEquals(dtpObj.isUserClickSubmitBtn(), true, "User clicks on submit button");
        });

        // validate test results
        Then("results should be populated as {string}", (String results) -> {
            Assert.assertEquals(dtpObj.userValidateResultsAs(), results, "Validate results in form");
        });

    }


}
