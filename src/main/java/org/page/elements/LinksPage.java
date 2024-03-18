package org.page.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LinksPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public LinksPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    @Step("Click the button, switch to new window and check URL")
    public void clickSwitchToNewWindowAndCheckUrl(String locator) {
        driver.findElement(By.id(locator)).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        webDriverWait.until(ExpectedConditions.urlContains("https://demoqa.com/"));
    }

    @Step("Click the link, send an api call and check the response text with status code")
    public Result clickTheLinkAndGetValidText(int status, String text, String id) {
        String expectedLinkResponse = "Link has responded with staus " + status + " and status text " + text;
        String linkResponse = "linkResponse";
        clickJavascriptExecutor8(driver.findElement(By.id(id)));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(linkResponse)));
        return new Result(expectedLinkResponse, linkResponse);
    }

    public record Result(String expectedLinkResponse, String linkResponse) {
    }

    private void clickJavascriptExecutor8(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}
