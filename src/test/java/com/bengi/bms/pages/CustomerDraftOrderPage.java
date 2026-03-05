package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;

public class CustomerDraftOrderPage extends BasePage {

    // --- LOCATOR'LAR ---
    private final By hourSelect = By.id("hourSelect");
    private final By sendToCustomerButton = By.xpath("//button[contains(., 'Send to Customer')]");
    private final By goToOrderFormButton = By.xpath("//a[contains(., 'Go to Order Form')]");

    private By cartItem(String productName, String packaging) {
        return By.xpath("//tr[contains(., '" + productName + "') and contains(., '" + packaging + "')]");
    }

    // --- METOTLAR ---
    public boolean isProductInCart(String productName, String packaging) {
        return isVisible(cartItem(productName, packaging));
    }

    public void selectTime(String timeText) {
        // "12 hours" metninin içindeki harfleri atıp sadece "12" rakamını alırız
        String numericValue = timeText.replaceAll("[^0-9]", "");

        click(hourSelect); // Önce dropdown'a tıkla ki açılsın
        // Sonra değeri 12 olan seçeneğe tıkla
        click(By.xpath("//select[@id='hourSelect']/option[@value='" + numericValue + "']"));
    }

    public void clickSendLink() {
        click(sendToCustomerButton);
    }

    public void bypassToOrderForm() {
        click(goToOrderFormButton);
    }

}