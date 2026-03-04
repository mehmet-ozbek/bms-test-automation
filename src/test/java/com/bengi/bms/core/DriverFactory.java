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

                    // GitHub Actions / CI ortamında headless daha stabil
                    boolean isCI = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));
                    if (isCI) {
                        options.addArguments("--headless=new");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--window-size=1920,1080");
                    }

                    driver = new ChromeDriver(options);
                    break;

                default:
                    throw new RuntimeException("Browser not supported: " + browser);
            }

            // Headless'ta maximize bazen anlamsız/zararlı; ama localde kalsın
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