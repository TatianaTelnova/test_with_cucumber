package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.AtmPage;
import org.pages.FaqPage;
import org.pages.MainPage;

public class StepDefinitions {
    private WebDriver driver;
    private Boolean exist;
    private Integer contentCount;
    private Integer atmCount;

    private Integer withFilterCount;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am on the MainPage")
    public void i_am_on_the_main_page() {
        driver.get("https://www.bspb.ru/");
    }

    @Given("I am on the AtmPage")
    public void i_am_on_the_atm_page() {
        driver.get("https://www.bspb.ru/map?is=bankomats");
    }

    @Given("I am on the FaqPage")
    public void i_am_on_the_faq_page() {
        driver.get("https://www.bspb.ru/retail/faq");
    }

    @When("I search for login button")
    public void i_search_for() {
        MainPage mp = new MainPage(driver);
        exist = mp.checkExistButtonLogin();
    }

    @When("I count elements in a content")
    public void i_count_elements_in_a_content() {
        MainPage mp = new MainPage(driver);
        contentCount = mp.countContentElems();
    }

    @When("I click on the first block item and count faq")
    public void i_click_on_a_block_item_no() {
        FaqPage fp = new FaqPage(driver);
        fp.clickButtonBlock();
        withFilterCount = fp.countFaq();
    }

    @When("I click on the ATM list button")
    public void i_click_on_the_atm_list_button() {
        AtmPage ap = new AtmPage(driver);
        ap.clickAtmButton();
        atmCount = ap.countAtm();
    }

    @Then("the result must be true")
    public void the_result_must_be_true() {
        Assert.assertTrue(exist);
    }

    @Then("the result must not be null")
    public void the_result_must_not_be_null() {
        Assert.assertTrue(contentCount > 0);
    }

    @Then("faq must be less than {int}")
    public void faq_must_be_less_than(Integer number) {
        Assert.assertTrue(withFilterCount < number);
    }

    @Then("ATM must be equals {int}")
    public void atm_must_be_equals(Integer number) {
        Assert.assertEquals(number, atmCount);
    }
}
