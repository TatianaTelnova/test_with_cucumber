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
    private Boolean existElem;

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

    @When("перехожу на страницу с банкоматами")
    public void goToAtmPage() {
        mp.clickGoToAtm();
    }

    @When("снова кликаю на кнопку Списком")
    public void clickAtmBtn() {
        AtmPage ap = new AtmPage(driver);
        ap.clickAtmButton();
    }

    @Then("снова количество адресов банкоматов равно {int}")
    public void atmMustBeEqualNumber(int number) {
        AtmPage ap = new AtmPage(driver);
        Assert.assertEquals(number, ap.countAtm());
        tearDown();
    }

    @When("проверяю присутствие кнопки Войти")
    public void searchForLoginBtn() {
        existElem = mp.checkExistButtonLogin();
    }

    @When("проверяю присутствие кнопки Связаться с нами")
    public void searchForContactBtn() {
        existElem = mp.checkExistContactBtn();
    }

    @Then("результат должен быть Присутствует")
    public void resultMustBeTrue() {
        Assert.assertTrue(existElem);
        tearDown();
    }

    @When("перехожу на страницу с частыми вопросами")
    public void goToFaqPage() {
        mp.clickGoToFaq();
    }

    @When("снова считаю количество блоков тем")
    public void countBlocks() {
        FaqPage fp = new FaqPage(driver);
        elemCount = fp.countBlocks();
    }

    @Then("снова результат равен {int}")
    public void faqEqualNumber(int number) {
        Assert.assertEquals(number, elemCount);
        tearDown();
    }
}
