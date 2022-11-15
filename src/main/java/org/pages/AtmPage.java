package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AtmPage extends BasePage {
    private HashMap<String, By> mapAtmPage = new HashMap<>() {
        {
            put("Списком", By.className("css-1frjy8u"));
            put("адреса банкоматов", By.className("css-uxn78q"));
        }
    };

    public AtmPage(WebDriver driver) {
        super(driver);
    }

    public void clickAtm(String elem) {
        clickElem(mapAtmPage.get(elem));
    }

    public int countAtm(String elem) {
        return countElems(mapAtmPage.get(elem));
    }
}
