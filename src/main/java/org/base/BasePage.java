package org.base;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Performing a click using JavascriptExecutor")
    public void clickJavascriptExecutor(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}
