package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public static final By BUTTON_LOGIN = By.xpath("//*[contains(@class,'css-sy2ljg')]");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkExistButtonLogin() {
        return checkExist(BUTTON_LOGIN);
    }

    public int countContentElems() {
        return countElems(CONTAINER_CONTENTS);
    }

    public void clickLoginBtn() {
        clickElem(BUTTON_LOGIN);
    }
}
