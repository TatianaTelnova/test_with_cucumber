package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.pages.AtmPage;

public class AtmStepDef extends BaseStepDef {
    private AtmPage ap;

    @Given("открыта страница с адресами банкоматов")
    public void openAtmPage() {
        setUp();
        driver.get("https://www.bspb.ru/map?is=bankomats");
        ap = new AtmPage(driver);
    }

    @When("кликаю на кнопку Списком")
    public void clickAtmBtn() {
        ap.clickAtmButton();
    }

    @Then("количество адресов банкоматов равно {int}")
    public void atmMustBeEqualNumber(int number) {
        Assert.assertEquals(number, ap.countAtm());
        tearDown();
    }
}
