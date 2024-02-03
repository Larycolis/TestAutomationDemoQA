package ui.elements;

import org.base.WebDriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxTests {
    private WebDriverSetup webDriverSetup;

    @BeforeEach
    void setUp() {
        webDriverSetup = new WebDriverSetup();
        webDriverSetup.setUp("https://demoqa.com/checkbox");
    }

    @AfterEach
    void tearDown() {
        webDriverSetup.tearDown();
    }

    @Test
    void clickCheckboxesAndCheckSelected() {
        WebDriver driver = getDriver();
        driver.findElement(By.cssSelector("button[aria-label='Expand all']")).click();
        driver.findElement(By.cssSelector("[for='tree-node-home'] span.rct-checkbox")).click();
        List<WebElement> webElements = driver.findElements(By.cssSelector("input[type='checkbox']"));
        List<WebElement> webElementsText = driver.findElements(By.className("text-success"));
        List<String> actualTitles = driver.findElements(By.cssSelector("span.rct-title")).stream()
                .map(el -> el.getText().replaceAll(".doc", "").replaceAll(" ", "").toLowerCase())
                .toList();
        List<String> expectedTitles = webElementsText.stream()
                .map(el -> el.getText().toLowerCase())
                .toList();
        Assertions.assertEquals(expectedTitles, actualTitles);
        Assertions.assertEquals(17, webElements.size());
        for (WebElement tmp : webElements) {
            Assertions.assertTrue(tmp.isSelected());
        }
    }

    private WebDriver getDriver() {
        return webDriverSetup.getDriver();
    }
}
