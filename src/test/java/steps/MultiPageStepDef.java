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
        windowHandles.add(driver.getWindowHandle());
    }

    @Given("открыта страница с частыми вопросами")
    public void openFaqPage() {
        driver.get("https://www.bspb.ru/retail/faq");
    }

    @Given("открыта страница с адресами банкоматов")
    public void openAtmPage() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
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
        new  MainPage(driver).clickGoToFaq(nav, elem);
    }

    @When("кликаю {string}")
    public void clickEveryElem(String elem) {
        AtmPage ap = new AtmPage(driver);
        FaqPage fp = new FaqPage(driver);
        MainPage mp = new  MainPage(driver);
        if (ap.checkMap(elem)) {
            ap.clickAtm(elem);
        } else if (fp.checkMap(elem)) {
            fp.clickFaq(elem);
        } else if (mp.checkMap(elem)) {
            mp.clickMain(elem);
        }
    }

    @When("считаю {string}")
    public void countEveryElems(String elems) {
        AtmPage ap = new AtmPage(driver);
        FaqPage fp = new FaqPage(driver);
        MainPage mp = new  MainPage(driver);
        if (ap.checkMap(elems)) {
            elemCount = ap.countAtm(elems);
        } else if (fp.checkMap(elems)) {
            elemCount = fp.countFaq(elems);
        } else if (mp.checkMap(elems)) {
            elemCount = mp.countMain(elems);
        }
    }

    @When("считаю отфильтрованные faq {string}")
    public void countFaqElemsWithFilter(String elems) {
        elemCount = new FaqPage(driver).countFaqWithFilter(elems);
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
    public void resultEqualNumber(int number) {
        Assert.assertEquals(number, elemCount);
    }

    @Then("{string} присутствует на странице")
    public void checkResult(String elem) {
        Assert.assertTrue(new MainPage(driver).checkExistMain(elem));
    }

    @Then("текст внутри {string} равен {string}")
    public void userName(String elem, String username) {
        System.out.println(new MainPage(driver).getTextMain(elem).trim());
        Assert.assertEquals(username, new MainPage(driver).getTextMain(elem).trim());
    }
}
