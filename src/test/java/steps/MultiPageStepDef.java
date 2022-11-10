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

    @When("перехожу на страницу с частыми вопросами")
    public void goToFaqPage() {
        mp.clickGoToFaq();
    }

    // элемент по клику
    @When("кликаю {string}")
    public void clickElem(String elem) {
        switch (elem) {
            case "Списком":
                ap.clickAtmButton();
                break;
            case "первая тема":
                fp.clickButtonBlock();
                break;
            case "Войти чк":
                windowHandles.add(driver.getWindowHandle());
                mp.clickLogin();
                break;
            case "Demo":
                wait.until(numberOfWindowsToBe(windowHandles.size() + 1));
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!windowHandles.contains(windowHandle)) {
                        windowHandles.add(windowHandle);
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }
                mp.clickContainerLogin();
                break;
            case "Войти в лк":
                wait.until(numberOfWindowsToBe(windowHandles.size() + 1));
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!windowHandles.contains(windowHandle)) {
                        windowHandles.add(windowHandle);
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }
                mp.clickDemoLogin();
                break;
            case "Войти":
                mp.clickDemoOtpLogin();
                break;
            default:
                break;
        }
    }

    @When("считаю {string}")
    public void countElems(String elems) {
        switch (elems) {
            case "вопросы":
                fp = new FaqPage(driver);
                elemCount = fp.countFaq();
                break;
            case "темы":
                fp = new FaqPage(driver);
                elemCount = fp.countBlocks();
                break;
            case "отфильтрованные вопросы":
                fp = new FaqPage(driver);
                elemCount = fp.countFaqWithFilter();
                break;
            case "элементы контента":
                elemCount = mp.countContentElems();
                break;
            case "адреса банкоматов":
                elemCount = ap.countAtm();
                break;
            default:
                break;
        }
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
    }

    @Then("имя пользователя равно {string}")
    public void userName(String username) {
        System.out.println(mp.getUserName().trim());
        Assert.assertEquals(username, mp.getUserName().trim());
    }
}
