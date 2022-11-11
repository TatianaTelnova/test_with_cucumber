package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.pages.UniPage;

import java.util.HashSet;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class MultiPageStepDef extends BaseStepDef {
    private UniPage up;
    private int elemCount;
    private final Set<String> windowHandles = new HashSet<>();

    private void switchNewWindow() {
        wait.until(numberOfWindowsToBe(windowHandles.size() + 1));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandles.contains(windowHandle)) {
                windowHandles.add(windowHandle);
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

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
        up = new UniPage(driver);
    }

    @Given("открыта страница с частыми вопросами")
    public void openFaqPage() {
        driver.get("https://www.bspb.ru/retail/faq");
        up = new UniPage(driver);
    }

    @Given("открыта страница с адресами банкоматов")
    public void openAtmPage() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
        up = new UniPage(driver);
    }

    @When("кликаю {string} для перехода на новую страницу")
    public void clickElemClassNameNewPage(String elem) {
        switchNewWindow();
        up.clickElemWithClassName(elem);
    }

    @When("кликаю {string} с индексом {int} для перехода на новую страницу")
    public void clickElemIdIdxNewPage(String elem, int idx) {
        switchNewWindow();
        up.clickElemWithClassName(elem, idx);
    }

    @When("кликаю элемент по id {string} для перехода на новую страницу")
    public void clickElemIdNewPage(String elem) {
        switchNewWindow();
        up.clickElemWithId(elem);
    }

    @When("считаю элементы контента")
    public void countContentElems() {
        elemCount = up.countContentElems();
    }

    @Then("результат больше {int}")
    public void resultMustBeGreaterThanNumber(int number) {
        Assert.assertTrue(elemCount > number);
    }

    @Then("результат меньше {int}")
    public void resultMustBeLessThanNumber(int number) {
        Assert.assertTrue(elemCount < number);
    }

    @Then("результат равен {int}")
    public void faqEqualNumber(int number) {
        Assert.assertEquals(number, elemCount);
    }

    @Then("текст внутри элемента {string} равен {string}")
    public void userName(String id, String username) {
        System.out.println(up.getUserName(id).trim());
        Assert.assertEquals(username, up.getUserName(id).trim());
    }

    @Then("элемент {string} присутствует на странице")
    public void checkBtn(String elems) {
        Assert.assertTrue(up.checkExistButton(elems));
    }

    @When("считаю элементы {string}")
    public void countElems(String elems) {
        elemCount = up.countElems(elems);
    }

    @When("считаю отфильтрованные элементы {string}")
    public void countElemsWithFilter(String elems) {
        elemCount = up.countElemsWithFilter(elems);
    }

    @When("кликаю элемент {string}")
    public void clickElemClassName(String elem) {
        up.clickElemWithClassName(elem);
    }

    @When("кликаю элемент по id {string}")
    public void clickElemId(String elem) {
        up.clickElemWithId(elem);
    }

    @When("перехожу по элементу {string} с индексом {int}")
    public void goToPage(String elem, int idx) {
        up.clickGoToPage(elem, idx);
    }
}
