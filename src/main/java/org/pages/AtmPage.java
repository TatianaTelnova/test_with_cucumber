package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AtmPage extends BasePage {
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public static final By BUTTON_TO_LIST = By.xpath("//*[contains(@class,'css-1frjy8u')]");
    public static final By ATM_ITEMS = By.xpath("//*[@class='css-uxn78q']");

    public AtmPage(WebDriver driver) {
        super(driver);
    }

    public void clickAtmButton() {
        wait.until(visibilityOfElementLocated(BUTTON_TO_LIST));
        clickElem(BUTTON_TO_LIST);
    }

    public int countAtm() {
        wait.until(visibilityOfElementLocated(ATM_ITEMS));
        return countElems(ATM_ITEMS);
    }
}
