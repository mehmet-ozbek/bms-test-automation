package com.bengi.bms.components;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;

public class NavigationMenu extends BasePage {

    public NavigationMenu() {
        super();
    }
    public void clickMenu(String menuName) {
        By menuLocator = By.xpath("//span[normalize-space()='" + menuName + "']");
        click(menuLocator); // BasePage.click -> elementToBeClickable ile bekler
    }
}