package org.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverSetup {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public void setUp(String path) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(path);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
