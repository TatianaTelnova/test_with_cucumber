package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected int countElems(By elem) {
        wait.until(elementToBeClickable(elem));
        return driver.findElements(elem).size();
    }

    protected void clickElem(By elem) {
        wait.until(elementToBeClickable(elem));
        driver.findElement(elem).click();
    }

    protected boolean checkExist(By elem) {
        try {
            driver.findElement(elem);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected String getText(By elem) {
        return driver.findElement(elem).getAttribute("innerText");
    }

    protected WebElement getElem(By elem) {
        wait.until(elementToBeClickable(elem));
        return driver.findElement(elem);
    }
}
