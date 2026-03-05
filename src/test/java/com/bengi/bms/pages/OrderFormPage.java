package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderFormPage extends BasePage {

    // Ekranda gördüğümüz "+" yazan ilk butonu bulur
    private final By plusButton = By.xpath("(//button[contains(., '+')])[1]");

    // "Bestellen" yazan butonu bulur
    private final By bestellenButton = By.xpath("//button[contains(., 'Bestellen')]");

    // TODO: Sipariş verildikten sonra çıkan başarı mesajının locator'ı (Bunu birazdan netleştireceğiz)
    private final By successMessage = By.xpath("//*[contains(text(), 'alındı') or contains(text(), 'erfolgreich')]");

    public void increaseQuantity(int clickCount) {
        // İstenen sayı kadar "+" butonuna tıklar (Basit bir for döngüsü)
        for (int i = 0; i < clickCount; i++) {
            click(plusButton);
            try { Thread.sleep(250); } catch (Exception ignored) {} // Animasyon/JS için çeyrek saniye bekleme
        }
    }

    public void clickBestellen() {
        click(bestellenButton);
    }

    public void acceptJavaScriptAlert() {
        // Selenium'a "Ekranda JS uyarısı çıkana kadar bekle, sonra OK (accept) tuşuna bas" diyoruz
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public boolean isSuccessMessageVisible() {
        return isVisible(successMessage);
    }
}