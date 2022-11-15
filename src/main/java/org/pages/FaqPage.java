package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.HashMap;

public class FaqPage extends BasePage {

    private HashMap<String, By> mapFaqPage = new HashMap<>() {
        {
            put("первая тема", By.id("accordion-button-17"));
            put("темы", By.className("css-1sfe36n"));
            put("вопросы", By.className("css-1cw6qg0"));
        }
    };

    public FaqPage(WebDriver driver) {
        super(driver);
    }

    public void clickFaq(String elem) {
        clickElem(mapFaqPage.get(elem));
    }

    public int countFaq(String elem) {
        return countElems(mapFaqPage.get(elem));
    }

    public int countFaqWithFilter(String elem) {
        wait.until((ExpectedCondition<Boolean>) driver -> countElems(mapFaqPage.get(elem)) != 100);
        return countElems(mapFaqPage.get(elem));
    }
}
