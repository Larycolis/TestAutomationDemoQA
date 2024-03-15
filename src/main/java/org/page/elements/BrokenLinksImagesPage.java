package org.page.elements;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;

import java.time.Duration;

public class BrokenLinksImagesPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final String brokenLinkPath = "https://the-internet.herokuapp.com/status_codes/500";
    @FindBy(css = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > img:nth-child(3)")
    private WebElement validImage;
    @FindBy(xpath = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > img:nth-child(7)")
    private WebElement brokenImage;
    @FindBy(css = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > a:nth-child(11)")
    private WebElement validLink;
    @FindBy(css = "div.col-12.mt-4.col-md-6 > div:nth-child(2) > a:nth-child(15)")
    private WebElement brokenLink;
    @FindBy(css = "#content > div > p")
    private WebElement brokenLinkPageBody;

    public BrokenLinksImagesPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Checking that the URLs is equals")
    public void clickValidLinkWaitAndCheckURL() {
        String path = "https://demoqa.com/";
        clickJavascriptExecutor5(validLink);
        waitUntilURLContainsThePath(path);
        Assertions.assertEquals(path, driver.getCurrentUrl());
    }


    @Step("Checking that the text is equals")
    public void clickBrokenLinkWaitAndCheckBrokenLinkPageBody() {
        clickJavascriptExecutor5(brokenLink);
        waitUntilURLContainsThePath(brokenLinkPath);
        Assertions.assertEquals("""
                        This page returned a 500 status code.

                        For a definition and common list of HTTP status codes, go here""",
                brokenLinkPageBody.getText());
    }

    @Step("Compare URLs and throw an exception if the URLs match")
    public void clickBrokenLinkWaitCompareUrlsAndThrowExceptionIfTheURLsMatch() {
        clickJavascriptExecutor5(brokenLink);
        waitUntilURLContainsThePath(brokenLinkPath);
        Assertions.assertThrows(AssertionFailedError.class,
                () -> Assertions.assertNotEquals(brokenLinkPath, driver.getCurrentUrl()));
    }

    @Step("Compare the sizes of expected and actual images")
    public void compareImageSize() {
        Assertions.assertEquals(new Dimension(347, 100), validImage.getSize());
    }

    @Step("Compare icon sizes and throw an exception if the sizes match 16x16")
    public void compareIconSizesAndThrowExceptionIfSizesIsMatches() {
        Assertions.assertThrows(InvalidSelectorException.class,
                () -> Assertions.assertNotEquals(new Dimension(16, 16),
                        brokenImage.getSize()));
    }

    private void waitUntilURLContainsThePath(String path) {
        webDriverWait.until(ExpectedConditions.urlContains(path));
    }

    private void clickJavascriptExecutor5(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}
