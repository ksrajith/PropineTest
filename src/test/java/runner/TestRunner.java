package runner;


import common.BasePage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

@CucumberOptions(
        plugin = { "pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber-report.json"},
        features = "src/test/resources/features/dateParser.feature",
        glue = {"testSteps.dateParser"}
)
public class TestRunner  extends AbstractTestNGCucumberTests {

    @BeforeTest
    public void testStart(){
        // open browser before starts feature execution
        BasePage.browser();
        BasePage.driver.navigate().to("https://vast-dawn-73245.herokuapp.com/");
        BasePage.driver.manage().window().maximize();
    }
    @AfterTest
    public void testEnd(){
        // close browser after feature execution
        BasePage.driver.close();
    }

}
