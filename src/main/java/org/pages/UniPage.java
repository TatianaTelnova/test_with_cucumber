package org.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class UniPage extends BasePage {
    public static final By CONTAINER_CONTENTS = By.xpath("//*[@id='app-wrapper']/main/div/div");
    public UniPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkExistButton(String identifier) {
        WebElement we = getElemByClassName(identifier);
        int deltaY = we.getRect().y;
        Actions action = new Actions(driver);
        action.scrollByAmount(0, deltaY).perform();
        return checkExistByClassName(identifier);
    }

    public int countElems(String identifier) {
        return countElemsByClassName(identifier);
    }

    public int countElemsWithFilter(String identifier) {
        wait.until((ExpectedCondition<Boolean>) driver -> countElemsByClassName(identifier) != 100);
        return countElemsByClassName(identifier);
    }

    public void clickElemWithClassName(String identifier) {
        clickElemByClassName(identifier);
    }

    public void clickElemWithClassName(String identifier, int idx) {
        clickElemByClassName(identifier, idx);
    }

    public void clickElemWithId(String identifier) {
        clickElemById(identifier);
    }

    public void clickGoToPage(String identifier, int idx) {
        WebElement we = getElemById();
        Actions action = new Actions(driver);
        action.moveToElement(we).perform();
        action.moveToElement(getElemByClassName(identifier, idx)).click().perform();
    }

    public int countContentElems() {
        return countElems();
    }

    public String getUserName(String identifier) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ignored) {}
        return getText(identifier);
    }
}
