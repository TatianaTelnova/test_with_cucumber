package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public static final By BUTTON_LOGIN = By.xpath("//*[contains(@class,'css-sy2ljg')]");
    public static final By BUTTON_CARDS = By.xpath("//*[@id='popover-trigger-4']");
    public static final By BUTTON_MAP = By.xpath("//*[contains(@class,'css-1juz3op')]");


    public static final By BUTTON_FAQ = By.xpath("(//*[contains(@class,'css-16fpbj')])[9]");
    public static final By BUTTON_CONTACT = By.xpath("//*[contains(@class,'css-ndfch2')]");
    public static final By CONTAINER_CONTENTS = By.xpath("//*[@id='app-wrapper']/main/div/div");
    public static final By CONTAINER_BLOCKS_ITEMS = By.xpath("//*[contains(@class,'css-1sfe36n')]");

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
}
