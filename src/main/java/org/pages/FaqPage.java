package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class FaqPage extends BasePage {
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public static final By BUTTON_BLOCK = By.xpath("//*[@id='accordion-button-17']");
    public static final By CONTAINER_BLOCKS_ITEMS = By.xpath("//*[contains(@class,'css-1sfe36n')]");
    public static final By CONTAINER_QUESTIONS_ITEMS = By.xpath("//*[contains(@class,'css-1cw6qg0')]");

    public FaqPage(WebDriver driver) {
        super(driver);
    }

    public void clickButtonBlock() {
        wait.until(elementToBeClickable(CONTAINER_QUESTIONS_ITEMS));
        clickElem(BUTTON_BLOCK);
    }

    public int countBlocks() {
        wait.until(elementToBeClickable(CONTAINER_BLOCKS_ITEMS));
        return countElems(CONTAINER_BLOCKS_ITEMS);
    }

    public int countFaq() {
        wait.until(elementToBeClickable(CONTAINER_QUESTIONS_ITEMS));
        return countElems(CONTAINER_QUESTIONS_ITEMS);
    }

    public int countFaqWithFilter() {
        wait.until((ExpectedCondition<Boolean>) driver -> countElems(CONTAINER_QUESTIONS_ITEMS) != 100);
        return countElems(CONTAINER_QUESTIONS_ITEMS);
    }
}
