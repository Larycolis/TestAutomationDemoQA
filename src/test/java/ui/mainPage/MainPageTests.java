package ui.mainPage;

import org.base.WebDriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageTests {
    private WebDriverSetup webDriverSetup;
    private final String path = "https://demoqa.com/";

    @BeforeEach
    void setUp() {
        webDriverSetup = new WebDriverSetup();
        webDriverSetup.setUp(path);
    }

    @AfterEach
    void tearDown() {
        webDriverSetup.tearDown();
    }

    //Подумать зачем этот тест и что он проверяет: Данный тест ничего не проверяет, кроме кликабельности логотипа для рефреша страницы
    @Test
    void clickLogoAndCheckStartPageRefreshedTest() {
        WebDriver driver = getDriver();
        driver.findElement(By.className("banner-image")).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("header > a")).isDisplayed());
    }

    @Test
    void clickBannerImageAndCheckSeleniumTrainingPageOpenedTest() {
        WebDriver driver = getDriver();
        WebDriverWait webDriverWait = getWebDriverWait(driver);
        driver.findElement(By.className("banner-image")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        webDriverWait.until(ExpectedConditions.titleIs("Tools QA - Selenium Training"));
    }

    @ParameterizedTest
    @CsvSource({"0, elements", "1, forms", "2, alertsWindows", "3, widgets", "4, interaction", "5, books"})
    void clickButtonsAndCheckRelevantPageOpenedTest(int index, String locator) {
        WebDriver driver = getDriver();
        WebDriverWait webDriverWait = getWebDriverWait(driver);
        List<WebElement> webElements = driver.findElements(By.cssSelector(".category-cards>div"));
        webElements.get(index).click();
        webDriverWait.until(ExpectedConditions.urlContains(path));
        Assertions.assertEquals(path + locator, driver.getCurrentUrl());
    }

    private WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private WebDriver getDriver() {
        return webDriverSetup.getDriver();
    }
}
