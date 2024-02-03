package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ButtonsTests extends BaseTest {

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/buttons");
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
}
