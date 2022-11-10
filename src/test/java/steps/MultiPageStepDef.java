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

public class MultiPageStepDef extends BaseStepDef {
    private MainPage mp;
    private FaqPage fp;
    private AtmPage ap;
    private int elemCount;

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

    @Given("открыта страница Demo личного кабинета")
    public void openDemoPage() {
        driver.get("https://idemo.bspb.ru/auth?response_type=code&client_id=1&redirect_uri=https%3A%2F%2Fidemo.bspb.ru%2Flogin%2Fsuccess&prefetch_uri=https%3A%2F%2Fidemo.bspb.ru%2Flogin%2Fprefetch&force_new_session=true");
        mp = new MainPage(driver);
    }

    @When("перехожу на страницу с частыми вопросами")
    public void goToFaqPage() {
        mp.clickGoToFaq();
    }

    @When("кликаю {string}")
    public void clickAtmBtn(String elem) {
        switch (elem) {
            case "Списком":
                ap.clickAtmButton();
                break;
            case "первая тема":
                fp.clickButtonBlock();
                break;
            case "Войти в лк":
                mp.clickDemoLogin();
                break;
//            case "Demo":
//                mp.clickLogin();
//                break;
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
    public void resultMustBeGreaterThanNumber(Integer number) {
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
