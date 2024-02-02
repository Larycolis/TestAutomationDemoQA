package ui.elements.buttons;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ButtonsTests {
    private WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/buttons");
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void clickButtonDoubleClickMeAndCheckSelectedTest() {
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.id("doubleClickBtn"))).perform();
        Assertions.assertEquals("You have done a double click", driver.findElement(By.id("doubleClickMessage")).getText());
    }

    @Test
    void clickButtonRightClickMeAndCheckSelectedTest() {
        Actions action = new Actions(driver);
        action.contextClick(driver.findElement(By.id("rightClickBtn"))).perform();
        Assertions.assertEquals("You have done a right click", driver.findElement(By.id("rightClickMessage")).getText());
    }

    @Test
    void clickButtonClickMeAndCheckSelectedTest() {
        driver.findElement(By.cssSelector("div.col-md-6 > div:nth-child(2) > div:nth-child(3) button")).click();
        Assertions.assertEquals("You have done a dynamic click", driver.findElement(By.id("dynamicClickMessage")).getText());
    }
}
