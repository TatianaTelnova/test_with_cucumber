package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.MainPage;

public class MainStepDef extends BaseStepDef {
    private final WebDriver driver = setUp();
    private Integer contentCount;

    @Given("I am on the MainPage")
    public void i_am_on_the_main_page() {
        driver.get("https://www.bspb.ru/");
    }

    @When("I count elements in a content")
    public void i_count_elements_in_a_content() {
        MainPage mp = new MainPage(driver);
        contentCount = mp.countContentElems();
    }

    @Then("the result must not be null")
    public void the_result_must_not_be_null() {
        Assert.assertTrue(contentCount > 0);
        tearDown();
    }

    @When("I go to the AtmPage")
    public void i_go_to_the_atm_page() {
        MainPage mp = new MainPage(driver);
        mp.clickGoToAtm();
    }
}
