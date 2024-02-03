package ui.elements;

import org.base.WebDriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RadioButtonTests {
    private WebDriverSetup webDriverSetup;
    private static final String TEXT_SUCCESS = "text-success";

    @BeforeEach
    void setUp() {
        webDriverSetup = new WebDriverSetup();
        webDriverSetup.setUp("https://demoqa.com/radio-button");
    }

    @AfterEach
    void tearDown() {
        webDriverSetup.tearDown();
    }

    @Test
    void clickButtonYesAndCheckSelectedTest() {
        WebDriver driver = getDriver();
        getJavascriptExecutor("yesRadio");
        Assertions.assertEquals("Yes", driver.findElement(By.className(TEXT_SUCCESS)).getText());
    }

    @Test
    void clickButtonImpressiveAndCheckSelectedTest() {
        WebDriver driver = getDriver();
        getJavascriptExecutor("impressiveRadio");
        Assertions.assertEquals("Impressive", driver.findElement(By.className(TEXT_SUCCESS)).getText());
    }

    @Test
    void clickButtonNoAndCheckUnavailabilityTest() {
        WebDriver driver = getDriver();
        Assertions.assertFalse(driver.findElement(By.id("noRadio")).isEnabled());
    }

    private void getJavascriptExecutor(String id) {
        WebDriver driver = getDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.id(id)));
    }

    private WebDriver getDriver() {
        return webDriverSetup.getDriver();
    }
}
