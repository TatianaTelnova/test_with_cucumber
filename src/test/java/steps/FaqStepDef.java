package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.FaqPage;

public class FaqStepDef extends BaseStepDef {
    private final WebDriver driver = setUp();
    private Integer withFilterCount;

    @Given("I am on the FaqPage")
    public void i_am_on_the_faq_page() {
        driver.get("https://www.bspb.ru/retail/faq");
    }

    @When("I click on the first block item and count faq")
    public void i_click_on_a_block_item_no() {
        FaqPage fp = new FaqPage(driver);
        fp.clickButtonBlock();
        withFilterCount = fp.countFaq();
    }

    @Then("faq must be less than {int}")
    public void faq_must_be_less_than(Integer number) {
        Assert.assertTrue(withFilterCount < number);
        tearDown();
    }
}
