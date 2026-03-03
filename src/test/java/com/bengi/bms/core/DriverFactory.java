package com.bengi.bms.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void initDriver() {

        if (DRIVER.get() == null) {

            String browser = ConfigReader.get("browser");
            WebDriver driver;

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();

                    // CI ortamını yakala (GitHub Actions CI=true set eder)
                    boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));

                    if (isCI) {
                        options.addArguments("--headless=new");
                        options.addArguments("--window-size=1920,1080");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-gpu");
                    }

                    driver = new ChromeDriver(options);
                    break;

                default:
                    throw new RuntimeException("Browser not supported: " + browser);
            }

            // Headless'ta maximize güvenilmez; window-size ile zaten çözdük
            driver.manage().window().maximize();

            DRIVER.set(driver);
        }
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}