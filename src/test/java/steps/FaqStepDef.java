package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.FaqPage;

public class FaqStepDef extends BaseStepDef {
    private final WebDriver driver = setUp();
    private int elemCount;

    @Given("открыта страница с частыми вопросами")
    public void openFaqPage() {
        driver.get("https://www.bspb.ru/retail/faq");
    }

    @When("нажимаю на первую тему")
    public void clickFirstBlockItemAndCountFaq() {
        FaqPage fp = new FaqPage(driver);
        fp.clickButtonBlock();
    }

    @When("считаю количество блоков тем")
    public void countBlocks() {
        FaqPage fp = new FaqPage(driver);
        elemCount = fp.countBlocks();
    }

    @When("считаю количество частых вопросов")
    public void countFaq() {
        FaqPage fp = new FaqPage(driver);
        elemCount = fp.countFaq();
    }

    @When("считаю количество отфильтрованных частых вопросов")
    public void countFaqWithFilter() {
        FaqPage fp = new FaqPage(driver);
        elemCount = fp.countFaqWithFilter();
    }

    @Then("количество частых вопросов должно быть меньше {int}")
    public void faqMustBeLessThanNumber(int number) {
        Assert.assertTrue(elemCount < number);
        tearDown();
    }

    @Then("результат равен {int}")
    public void faqEqualNumber(int number) {
        Assert.assertEquals(number, elemCount);
        tearDown();
    }
}
