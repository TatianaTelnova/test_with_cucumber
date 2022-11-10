package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected int countElems(By elem) {
        return driver.findElements(elem).size();
    }

    protected void clickElem(By elem) {
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
        return driver.findElement(elem);
    }
}
