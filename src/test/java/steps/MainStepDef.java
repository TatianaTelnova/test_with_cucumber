package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.AtmPage;
import org.pages.MainPage;

public class MainStepDef extends BaseStepDef {
    private final WebDriver driver = setUp();
    private Integer contentCount;
    private Boolean exist;

    @Given("открыта главная страница")
    public void openMainPage() {
        driver.get("https://www.bspb.ru/");
    }

    @When("я считаю количество элементов контента")
    public void countContentElems() {
        MainPage mp = new MainPage(driver);
        contentCount = mp.countContentElems();
    }

    @Then("результат больше {int}")
    public void resultMustBeGreaterThanNumber(Integer number) {
        Assert.assertTrue(contentCount > number);
        tearDown();
    }

    @When("я перехожу на страницу с банкоматами")
    public void goToAtmPage() {
        MainPage mp = new MainPage(driver);
        mp.clickGoToAtm();
//        AtmPage ap = new AtmPage(driver);
//        ap.clickAtmButton();
    }

    @When("я снова кликаю на кнопку Списком")
    public void  clickAtmBtn() {
        AtmPage ap = new AtmPage(driver);
        ap.clickAtmButton();
    }

    @Then("снова количество адресов банкоматов равно {int}")
    public void atmMustBeEqualNumber(int number) {
        AtmPage ap = new AtmPage(driver);
        Assert.assertEquals(number, ap.countAtm());
        tearDown();
    }

    @When("я проверяю присутствие кнопки Войти")
    public void searchForLoginBtn() {
        MainPage mp = new MainPage(driver);
        exist = mp.checkExistButtonLogin();
    }

    @Then("результат должен быть Присутствует")
    public void resultMustBeTrue() {
        Assert.assertTrue(exist);
        tearDown();
    }
}
