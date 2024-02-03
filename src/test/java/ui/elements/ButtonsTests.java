package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class ButtonsTests extends BaseTest {

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/buttons");
    }

    @Test
    void clickButtonDoubleClickMeAndCheckSelectedTest() {
        Actions action = new Actions(getDriver());
        action.doubleClick(getDriver().findElement(By.id("doubleClickBtn"))).perform();
        Assertions.assertEquals("You have done a double click", getDriver().findElement(By.id("doubleClickMessage")).getText());
    }

    @Test
    void clickButtonRightClickMeAndCheckSelectedTest() {
        Actions action = new Actions(getDriver());
        action.contextClick(getDriver().findElement(By.id("rightClickBtn"))).perform();
        Assertions.assertEquals("You have done a right click", getDriver().findElement(By.id("rightClickMessage")).getText());
    }

    @Test
    void clickButtonClickMeAndCheckSelectedTest() {
        getDriver().findElement(By.cssSelector("div.col-md-6 > div:nth-child(2) > div:nth-child(3) button")).click();
        Assertions.assertEquals("You have done a dynamic click", getDriver().findElement(By.id("dynamicClickMessage")).getText());
    }
}
