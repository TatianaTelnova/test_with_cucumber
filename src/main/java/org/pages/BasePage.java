package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected int countElems() {
        wait.until(elementToBeClickable(UniPage.CONTAINER_CONTENTS));
        return driver.findElements(UniPage.CONTAINER_CONTENTS).size();
    }

    protected int countElemsByClassName(String classname) {
        wait.until(elementToBeClickable(By.className(classname)));
        return driver.findElements(By.className(classname)).size();
    }

    protected void clickElemByClassName(String classname) {
        wait.until(elementToBeClickable(By.className(classname)));
        driver.findElement(By.className(classname)).click();
    }

    protected void clickElemByClassName(String classname, int idx) {
        wait.until(elementToBeClickable(By.className(classname)));
        driver.findElements(By.className(classname)).get(idx).click();
    }

    protected void clickElemById(String id) {
        wait.until(elementToBeClickable(By.id(id)));
        driver.findElement(By.id(id)).click();
    }

    protected boolean checkExistByClassName(String classname) {
        try {
            driver.findElement(By.className(classname));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected WebElement getElemByClassName(String classname) {
        wait.until(elementToBeClickable(By.className(classname)));
        return driver.findElement(By.className(classname));
    }

    protected WebElement getElemByClassName(String classname, int idx) {
        wait.until(elementToBeClickable(By.className(classname)));
        return driver.findElements(By.className(classname)).get(idx);
    }

    protected WebElement getElemById() {
        wait.until(elementToBeClickable(By.id("popover-trigger-4")));
        return driver.findElement(By.id("popover-trigger-4"));
    }

    protected String getText(String id) {
        return driver.findElement(By.id(id)).getAttribute("innerText");
    }
}
