package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.pages.AtmPage;
import org.pages.FaqPage;
import org.pages.MainPage;

import java.util.HashSet;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class MultiPageStepDef extends BaseStepDef {
    private MainPage mp;
    private FaqPage fp;
    private AtmPage ap;
    private int elemCount;
    private final Set<String> windowHandles = new HashSet<>();

    @Before
    public void driverSetUp() {
        setUp();
    }

    @After
    public void driverTearDown() {
        tearDown();
    }

    @Given("открыта главная страница")
    public void openMainPage() {
        driver.get("https://www.bspb.ru/");
        mp = new MainPage(driver);
        windowHandles.add(driver.getWindowHandle());
    }

    @Given("открыта страница с частыми вопросами")
    public void openFaqPage() {
        driver.get("https://www.bspb.ru/retail/faq");
        fp = new FaqPage(driver);
    }

    @Given("открыта страница с адресами банкоматов")
    public void openAtmPage() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
        ap = new AtmPage(driver);
    }

    @When("перехожу на открытую вкладку")
    public void goToTab() {
        wait.until(numberOfWindowsToBe(windowHandles.size() + 1));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandles.contains(windowHandle)) {
                windowHandles.add(windowHandle);
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @When("кликаю на {string}, выбираю {string}")
    public void goToPage(String nav, String elem) {
        mp.clickGoToFaq(nav, elem);
    }

    @When("кликаю main {string}")
    public void clickMainElem(String elem) {
        mp.clickMain(elem);
    }

    @When("кликаю faq {string}")
    public void clickFaqElem(String elem) {
        fp.clickFaq(elem);
    }

    @When("кликаю atm {string}")
    public void clickAtmElem(String elem) {
        ap.clickAtm(elem);
    }

    @When("считаю faq {string}")
    public void countFaqElems(String elems) {
        fp = new FaqPage(driver);
        elemCount = fp.countFaq(elems);
    }

    @When("считаю отфильтрованные faq {string}")
    public void countFaqElemsWithFilter(String elems) {
        fp = new FaqPage(driver);
        elemCount = fp.countFaqWithFilter(elems);
    }

    @When("считаю main {string}")
    public void countMainElems(String elems) {
        mp = new MainPage(driver);
        elemCount = mp.countMain(elems);
    }

    @When("считаю atm {string}")
    public void countAtmElems(String elems) {
        ap = new AtmPage(driver);
        elemCount = ap.countAtm(elems);
    }

    @Then("результат больше {int}")
    public void resultMustBeGreaterThanNumber(int number) {
        Assert.assertTrue(elemCount > number);
    }

    @Then("результат меньше {int}")
    public void resultMustBeLessThanNumber(int number) {
        Assert.assertTrue(elemCount < number);
        tearDown();
    }

    @Then("результат равен {int}")
    public void faqEqualNumber(int number) {
        Assert.assertEquals(number, elemCount);
    }

    @Then("{string} присутствует на странице")
    public void checkBtn(String elem) {
        Assert.assertTrue(mp.checkExistMain(elem));
    }

    @Then("текст внутри {string} равен {string}")
    public void userName(String elem, String username) {
        System.out.println(mp.getTextMain(elem).trim());
        Assert.assertEquals(username, mp.getTextMain(elem).trim());
    }
}
