package org.page.alertsWindows;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserWindowsPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    @FindBy(id = "tabButton")
    private WebElement tabButton;

    @FindBy(id = "windowButton")
    private WebElement windowButton;

    @FindBy(id = "messageWindowButton")
    private WebElement messageWindowButton;

    public BrowserWindowsPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Performing a click using JavascriptExecutor")
    public void clickJavascriptExecutor4(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    @Step("Click the button, wait, switch and return new window")
    public String clickToTabButtonAndSwitchToNewWindow() {
        String originWindow = driver.getWindowHandle();
        clickJavascriptExecutor4(tabButton);
        webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToNewWindow(originWindow);
        return originWindow;
    }

    @Step("Click the button, wait, switch and return child window")
    public String clickToWindowButtonAndSwitchToChildWindow() {
        String originWindow = driver.getWindowHandle();
        clickJavascriptExecutor4(windowButton);
        webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToNewWindow(originWindow);
        return originWindow;
    }

    @Step("Click the button, wait, switch and return message window")
    public String clickToMessageWindowButtonAndSwitchToChildWindow() {
        String originWindow = driver.getWindowHandle();
        clickJavascriptExecutor4(messageWindowButton);
        webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        switchToNewWindow(originWindow);
        return originWindow;
    }

    @Step("Checking contains the expected URL and the text in new window")
    public void assertUrlAndValue() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://demoqa.com/sample"));
        Assertions.assertEquals("This is a sample page", driver.findElement(By.id("sampleHeading")).getText());
    }

    @Step("Close new window and switch to the origin window")
    public void closeChildWindowAndSwitchToOrigin(String originWindow) {
        driver.close();
        driver.switchTo().window(originWindow);
    }

    @Step("Checking that the values are not equal")
    public void checkTheValuesAreNotEqual(String originWindow) {
        Assertions.assertNotEquals(originWindow, driver.getWindowHandle());
    }

    private void switchToNewWindow(String originWindow) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
