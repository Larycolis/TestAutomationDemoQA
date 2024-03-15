package org.page.alertsWindows;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    @FindBy(id = "confirmButton")
    private WebElement confirmButton;
    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertButton;
    @FindBy(id = "promtButton")
    private WebElement promtButton;
    @FindBy(id = "alertButton")
    private WebElement alertButton;
    @FindBy(id = "confirmResult")
    private WebElement confirmResult;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Click and switch to the alert")
    public void clickAlertButtonAndDismiss() {
        clickJavascriptExecutor3(confirmButton);
        driver.switchTo().alert().dismiss();
    }

    @Step("Enter the name and confirm")
    public void enterValueAndConfirm(String name) {
        Alert alert = getAlert(promtButton);
        alert.sendKeys(name);
        alert.accept();
    }

    @Step("Assert text in confirm button alert")
    public void assertTextInConfirmButtonAlert(String expected) {
        Assertions.assertEquals(expected, getAlert(confirmButton).getText());
    }

    @Step("Assert text in alert button alert")
    public void assertTextInAlertButtonAlert(String expected) {
        Assertions.assertEquals(expected, getAlert(alertButton).getText());
    }

    @Step("Assert text in timer alert button alert")
    public void assertTextInTimerAlertButtonAlert(String expected) {
        Assertions.assertEquals(expected, getAlert(timerAlertButton).getText());
    }

    @Step("After the alert is closed, the user is on the current page")
    public void assertCurrentUrl(String currentUrl) {
        Assertions.assertEquals(currentUrl, driver.getCurrentUrl());
    }

    @Step("Checking confirmation of the OK button selection")
    public void checkConfirmationOk() {
        Assertions.assertEquals("You selected Ok", confirmResult.getText());
    }

    @Step("Checking confirmation of the Cancel button selection")
    public void checkConfirmationCancel() {
        Assertions.assertEquals("You selected Cancel", confirmResult.getText());
    }

    @Step("Checking the text in the alert for conformity")
    public void checkPromtAlertButtonText() {
        Assertions.assertEquals("Please enter your name", getAlert(promtButton).getText());
    }

    @Step("check the confirmation of the entered name")
    public void checkConfirmationName(String name) {
        Assertions.assertEquals("You entered " + name, driver.findElement(By.id("promptResult")).getText());
    }

    private void clickJavascriptExecutor3(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    private Alert getAlert(WebElement element) {
        clickJavascriptExecutor3(element);
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }
}
