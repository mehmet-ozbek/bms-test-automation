package com.bengi.bms.pages;

import com.bengi.bms.core.BasePage;
import org.openqa.selenium.By;

public class CustomersPage extends BasePage {

    // TODO: Bu locator'ları (id, xpath, css) kendi projendeki gerçek değerlerle güncellemelisin
    private final By searchInput = By.id("searchInput");
    private final By filterButton = By.xpath("//button[@type='submit' and contains(., 'Filter')]"); // Hatırlattığın Buton!

    // PRO DOKUNUŞ: Müşteri adına göre, o müşterinin kartındaki veya satırındaki sepet butonunu dinamik bulan locator
    private By basketButton(String customerName) {
        return By.xpath("//a[contains(., 'Basket')]");
    }

    public void searchAndFilterCustomer(String customerName) {
        type(searchInput, customerName);
        click(filterButton); // Söylediğin gibi filtreleme butonuna basıyoruz
    }

    public void clickBasket(String customerName) {
        click(basketButton(customerName));
    }
}