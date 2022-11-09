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

    @Given("открыта страница с адресами банкоматов")
    public void openAtmPage() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
    }

    @When("я кликаю на кнопку Списком")
    public void clickAtmBtn() {
        AtmPage ap = new AtmPage(driver);
        ap.clickAtmButton();
        atmCount = ap.countAtm();
    }

    @Then("количество адресов банкоматов равно {int}")
    public void atmMustBeEqualNumber(Integer number) {
        Assert.assertEquals(number, atmCount);
        tearDown();
    }
}
