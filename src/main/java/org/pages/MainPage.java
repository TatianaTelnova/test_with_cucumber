package org.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MainPage extends BasePage {
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public static final By BUTTON_LOGIN = By.xpath("//*[contains(@class,'css-sy2ljg')]");
    public static final By BUTTON_CARDS = By.xpath("//*[@id='popover-trigger-4']");
    public static final By BUTTON_MAP = By.xpath("//*[contains(@class,'css-1juz3op')]");
    public static final By BUTTON_FAQ = By.xpath("(//*[contains(@class,'css-16fpbj')])[9]");
    public static final By BUTTON_CONTACT = By.xpath("//*[contains(@class,'css-ndfch2')]");
    public static final By CONTAINER_CONTENTS = By.xpath("//*[@id='app-wrapper']/main/div/div");

    public static final By CONTAINER_LOGIN = By.xpath("(//*[contains(@class,'inline-block')])[3]");
    public static final By DEMO_LOGIN = By.xpath("//*[@id='login-button']");
    public static final By DEMO_OTP_LOGIN = By.xpath("//*[@id='login-otp-button']");
    public static final By USER_NAME = By.xpath("//*[@id='representee-name']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkExistButtonLogin() {
        return checkExist(BUTTON_LOGIN);
    }

    public int countContentElems() {
        return countElems(CONTAINER_CONTENTS);
    }

    public void clickGoToFaq() {
        WebElement we = getElem(BUTTON_CARDS);
        Actions action = new Actions(driver);
        action.moveToElement(we).perform();
        action.moveToElement(getElem(BUTTON_FAQ)).click().perform();
    }

    public void clickLogin() {
        clickElem(BUTTON_LOGIN);
    }

    public void clickContainerLogin() {
        clickElem(CONTAINER_LOGIN);
    }

    public void clickDemoLogin() {
        wait.until(visibilityOfElementLocated(DEMO_LOGIN));
        clickElem(DEMO_LOGIN);
    }

    public void clickDemoOtpLogin() {
        wait.until(visibilityOfElementLocated(DEMO_OTP_LOGIN));
        clickElem(DEMO_OTP_LOGIN);
    }

    public boolean checkExistContactBtn() {
        wait.until(elementToBeClickable(BUTTON_CONTACT));
        WebElement we = getElem(BUTTON_CONTACT);
        int deltaY = we.getRect().y;
        Actions action = new Actions(driver);
        action.scrollByAmount(0, deltaY).perform();
        return checkExist(BUTTON_CONTACT);
    }

    public String getUserName() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {}
        return getText(USER_NAME);
    }
}
