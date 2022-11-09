package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.pages.MainPage;

public class MainStepDef extends BaseStepDef {
    private final WebDriver driver = setUp();
    private Integer contentCount;
    private Boolean exist;

    @Given("открыта главная страница")
    public void i_am_on_the_main_page() {
        driver.get("https://www.bspb.ru/");
    }

    @When("я считаю количество элементов контента")
    public void i_count_elements_in_a_content() {
        MainPage mp = new MainPage(driver);
        contentCount = mp.countContentElems();
    }

    @Then("результат больше {int}")
    public void the_result_must_not_be_null(Integer number) {
        Assert.assertTrue(contentCount > number);
        tearDown();
    }

    @When("я перехожу на страницу с банкоматами")
    public void i_go_to_the_atm_page() {
        MainPage mp = new MainPage(driver);
        mp.clickGoToAtm();
    }

    @Then("я проверяю что-то")
    public void check_anything() {
        Assert.assertTrue(true);
        tearDown();
    }

    @When("я проверяю присутствие кнопки Войти")
    public void i_search_for() {
        MainPage mp = new MainPage(driver);
        exist = mp.checkExistButtonLogin();
    }

    @Then("результат должен быть Присутствует")
    public void the_result_must_be_true() {
        Assert.assertTrue(exist);
        tearDown();
    }
}
