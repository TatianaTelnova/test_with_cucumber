package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.AtmPage;

public class AtmStepDef extends BaseStepDef {
    private final WebDriver driver = setUp();

    @Given("открыта страница с адресами банкоматов")
    public void openAtmPage() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
    }

    @When("я кликаю на кнопку Списком")
    public void clickAtmBtn() {
        AtmPage ap = new AtmPage(driver);
        ap.clickAtmButton();
    }

    @Then("количество адресов банкоматов равно {int}")
    public void atmMustBeEqualNumber(int number) {
        AtmPage ap = new AtmPage(driver);
        Assert.assertEquals(number, ap.countAtm());
        tearDown();
    }
}
