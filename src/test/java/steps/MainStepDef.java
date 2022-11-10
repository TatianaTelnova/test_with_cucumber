package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.AtmPage;
import org.pages.FaqPage;
import org.pages.MainPage;

public class MainStepDef extends BaseStepDef {
    private MainPage mp;
    private int elemCount;

    @Given("открыта главная страница")
    public void openMainPage() {
        setUp();
        driver.get("https://www.bspb.ru/");
        mp = new MainPage(driver);
    }

    @When("считаю количество элементов контента")
    public void countContentElems() {
        elemCount = mp.countContentElems();
    }

    @Then("результат больше {int}")
    public void resultMustBeGreaterThanNumber(Integer number) {
        Assert.assertTrue(elemCount > number);
        tearDown();
    }

    @When("перехожу на страницу с частыми вопросами")
    public void goToFaqPage() {
        mp.clickGoToFaq();
    }

    @When("снова считаю {string}")
    public void countElemsFP(String elems) {
        FaqPage fp = new FaqPage(driver);
        switch (elems) {
            case "вопросы":
                elemCount = fp.countFaq();
                break;
            case "темы":
                elemCount = fp.countBlocks();
                break;
            default:
                break;
        }
    }

    @Then("снова результат равен {int}")
    public void faqEqualNumber(int number) {
        Assert.assertEquals(number, elemCount);
        tearDown();
    }

    @Then("{string} присутствует на странице")
    public void checkLoginBtn(String elems) {
        switch (elems) {
            case "Войти":
                Assert.assertTrue(mp.checkExistButtonLogin());
                break;
            case "Связаться с нами":
                Assert.assertTrue(mp.checkExistContactBtn());
                break;
            default:
                break;
        }
        tearDown();
    }
}
