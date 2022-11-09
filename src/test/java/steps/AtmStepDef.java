package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.AtmPage;

public class AtmStepDef extends BaseStepDef {
    private final WebDriver driver = setUp();
    private Integer atmCount;

    @Given("I am on the AtmPage")
    public void i_am_on_the_atm_page() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
    }

    @When("I click on the ATM list button")
    public void i_click_on_the_atm_list_button() {
        AtmPage ap = new AtmPage(driver);
        ap.clickAtmButton();
        atmCount = ap.countAtm();
    }

    @Then("ATM must be equals {int}")
    public void atm_must_be_equals(Integer number) {
        Assert.assertEquals(number, atmCount);
        tearDown();
    }
}
