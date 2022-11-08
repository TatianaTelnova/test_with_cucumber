package elementexists;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.MainPage;

public class StepDefinitions {
    private WebDriver driver;
    private Boolean ans;
    private int elemsCount;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on the MainPage")
    public void i_am_on_the_main_page() {
        driver.get("https://www.bspb.ru/");
    }

    @When("I search for login button")
    public void i_search_for() {
        MainPage mp = new MainPage(driver);
        ans = mp.checkExistButtonLogin();
    }

    @Then("the result must be true")
    public void the_result_must_be_true() {
        Assert.assertTrue(ans);
    }

    @When("I count elements in a content")
    public void i_count_elements_in_a_content() {
        MainPage mp = new MainPage(driver);
        elemsCount = mp.countContentElems();
    }

    @Then("the result must not be null")
    public void the_result_must_not_be_null() {
        Assert.assertTrue(elemsCount > 0);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
