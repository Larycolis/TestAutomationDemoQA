package ui.elements;

import org.base.WebDriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ButtonsTests {
    private WebDriverSetup webDriverSetup;

    @BeforeEach
    void setUp() {
        webDriverSetup = new WebDriverSetup();
        webDriverSetup.setUp("https://demoqa.com/buttons");
    }

    @AfterEach
    void tearDown() {
        webDriverSetup.tearDown();
    }

    @Test
    void clickButtonDoubleClickMeAndCheckSelectedTest() {
        WebDriver driver = getDriver();
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.id("doubleClickBtn"))).perform();
        Assertions.assertEquals("You have done a double click", driver.findElement(By.id("doubleClickMessage")).getText());
    }

    @Test
    void clickButtonRightClickMeAndCheckSelectedTest() {
        WebDriver driver = getDriver();
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(By.id("rightClickBtn"))).perform();
        Assertions.assertEquals("You have done a right click", driver.findElement(By.id("rightClickMessage")).getText());
    }

    @Test
    void clickButtonClickMeAndCheckSelectedTest() {
        WebDriver driver = getDriver();
        driver.findElement(By.cssSelector("div.col-md-6 > div:nth-child(2) > div:nth-child(3) button")).click();
        Assertions.assertEquals("You have done a dynamic click", driver.findElement(By.id("dynamicClickMessage")).getText());
    }

    private WebDriver getDriver() {
        return webDriverSetup.getDriver();
    }
}
