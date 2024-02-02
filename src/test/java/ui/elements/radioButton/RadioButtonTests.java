package ui.elements.radioButton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RadioButtonTests {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String path = "https://demoqa.com/radio-button";
        driver.get(path);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void clickButtonYesAndCheckSelectedTest() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("yesRadio")));
        Assertions.assertEquals("Yes", driver.findElement(By.className("text-success")).getText());
    }

    @Test
    void clickButtonImpressiveAndCheckSelectedTest() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id("impressiveRadio")));
        Assertions.assertEquals("Impressive", driver.findElement(By.className("text-success")).getText());
    }

    @Test
    void clickButtonNoAndCheckUnavailabilityTest() {
        Assertions.assertFalse(driver.findElement(By.id("noRadio")).isEnabled());
    }
}
