package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.pages.FaqPage;

public class FaqStepDef extends BaseStepDef {
    private FaqPage fp;
    private int elemCount;

    @Given("открыта страница с частыми вопросами")
    public void openFaqPage() {
        setUp();
        driver.get("https://www.bspb.ru/retail/faq");
        fp = new FaqPage(driver);
    }

    @When("нажимаю на первую тему")
    public void clickFirstBlockItemAndCountFaq() {
        fp.clickButtonBlock();
    }

    @When("считаю (блоки тем)")
    public void countBlocks() {
        elemCount = fp.countBlocks();
    }

    @When("считаю (частые вопросы)")
    public void countFaq() {
        elemCount = fp.countFaq();
    }

    @When("считаю количество отфильтрованных частых вопросов")
    public void countFaqWithFilter() {
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
