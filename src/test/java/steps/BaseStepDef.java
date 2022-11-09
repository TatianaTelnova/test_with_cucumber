package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseStepDef {
    public WebDriver driver;

    public WebDriver setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void tearDown() {
        driver.quit();
    }
}
