package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AtmPage extends BasePage {
    public static final By BUTTON_TO_LIST = By.xpath("//*[contains(@class,'css-1frjy8u')]");
    public static final By ATM_ITEMS = By.xpath("//*[@class='css-uxn78q']");

    public AtmPage(WebDriver driver) {
        super(driver);
    }

    public void clickAtmButton() {
        clickElem(BUTTON_TO_LIST);
    }

    public int countAtm() {
        return countElems(ATM_ITEMS);
    }
}
