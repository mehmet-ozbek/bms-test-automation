package com.bengi.bms.components;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class NavigationMenu extends BasePage {

    public NavigationMenu() {
        super();
    }

    public void clickMenu(String menuName) {
        // 1. ADIM: Daha esnek bir locator (contains kullanarak Linux uyumluluğu sağlıyoruz)
        By menuLocator = By.xpath("//a[.//text()[contains(.,'" + menuName + "')]]");

        try {
            // 2. ADIM: Menü kapalı mı kontrol et (GitHub/CI dar ekran sorunu için)
            // Sol üstteki o 3 çizgi butonunu (hamburger) bulmaya çalışıyoruz
            By hamburgerMenuLocator = By.cssSelector(".navbar-toggler, .menu-toggle, i.fa-bars, [data-bs-toggle='sidebar']");

            if (!driver.findElements(hamburgerMenuLocator).isEmpty()) {
                WebElement hamburger = driver.findElement(hamburgerMenuLocator);
                // Eğer hamburger menü görünürse, menü kapalı demektir; açmak için tıkla
                if (hamburger.isDisplayed()) {
                    hamburger.click();
                    Thread.sleep(500); // Menünün yana doğru açılması için yarım saniye bekle
                }
            }
        } catch (Exception ignored) {
            // Eğer hamburger menü yoksa veya zaten açıksa hata vermeden devam et
        }

        try {
            // 3. ADIM: Standart tıklamayı dene
            click(menuLocator);
        } catch (Exception e) {
            // 4. ADIM: Eğer standart tıklama (görünürlük yüzünden) patlarsa, JS ile zorla tıkla
            WebElement element = driver.findElement(menuLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}