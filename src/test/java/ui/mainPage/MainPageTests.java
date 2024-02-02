package ui.mainPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPageTests {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final String path = "https://demoqa.com/";

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(path);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void clickLogoAndCheckStartPageRefreshedTest() {
        Assertions.assertTrue(driver.findElement(By.cssSelector("header > a")).isDisplayed());
        driver.findElement(By.className("banner-image")).click();
    }

    @Test
    void clickBannerImageAndCheckSeleniumTrainingPageOpenedTest() {
        driver.findElement(By.className("banner-image")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        webDriverWait.until(ExpectedConditions.titleIs("Tools QA - Selenium Training"));
    }

    @ParameterizedTest
    @CsvSource({"0, elements", "1, forms", "2, alertsWindows", "3, widgets", "4, interaction", "5, books"})
    void clickButtonsAndCheckRelevantPageOpenedTest(int index, String locator) {
        List<WebElement> webElements = driver.findElements(By.cssSelector(".category-cards>div"));
        webElements.get(index).click();
        webDriverWait.until(ExpectedConditions.urlContains(path));
        Assertions.assertEquals(path + locator, driver.getCurrentUrl());
    }
}
