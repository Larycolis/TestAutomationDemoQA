package ui.elements;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxTests extends BaseTest {
    private BaseTest baseTest;

    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/checkbox");
    }

    @Test
    void clickCheckboxesAndCheckSelected() {
        getDriver().findElement(By.cssSelector("button[aria-label='Expand all']")).click();
        getDriver().findElement(By.cssSelector("[for='tree-node-home'] span.rct-checkbox")).click();
        List<WebElement> webElements = getDriver().findElements(By.cssSelector("input[type='checkbox']"));
        List<WebElement> webElementsText = getDriver().findElements(By.className("text-success"));
        List<String> actualTitles = getDriver().findElements(By.cssSelector("span.rct-title")).stream()
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
}
