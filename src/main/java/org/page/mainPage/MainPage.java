package org.page.mainPage;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    @FindBy(css = ".category-cards>div")
    private List<WebElement> cardList;
    @FindBy(className = "banner-image")
    private WebElement bannerImage;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @Step("Click the banner and switch to new window")
    public void clickBannerAndSwitchToNewWindow() {
        clickJavascriptExecutor2(bannerImage);
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }

    @Step("Wait title is")
    public void waitTitleIs() {
        webDriverWait.until(ExpectedConditions.titleIs("Tools QA - Selenium Training"));
    }

    @Step("Wait URL is")
    public void waitUrlIs(String path) {
        webDriverWait.until(ExpectedConditions.urlContains(path));
    }

    @Step("Click on the card")
    public void clickOnTheCard(int index) {
        cardList.get(index).click();
    }

    @Step("Assert equality of expected and actual URL")
    public void assertActualUrl(String path) {
        Assertions.assertEquals(path, driver.getCurrentUrl());
    }

    public void clickJavascriptExecutor2(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    } //TODO: перенести в BasePage
}
