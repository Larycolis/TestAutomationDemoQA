package ui.elements;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@ExtendWith(AllureJunit5.class)
@Feature("Checkbox")
@Story("UI Tests")
public class CheckboxTests extends BaseTest {
    @BeforeEach
    void setUp() {
        getDriver().get("https://demoqa.com/checkbox");
    }

    @Test
    @DisplayName("Click on the root checkbox and check that all checkboxes are selected")
    void clickRootCheckboxAndCheckAllCheckboxesIsSelected() {
        clickExpandAll();
        clickRootCheckBox();
        List<String> expectedTitles = getExpectedTitles(By.className("text-success"));
        List<String> actualTitles = getActualTitles(By.cssSelector("span.rct-title"));
        List<WebElement> webElements = getDriver().findElements(By.cssSelector("input[type='checkbox']"));
        checkEqualsExpAndActTitles(expectedTitles, actualTitles);
        checkSizeEquality(webElements);
        checkSelectedCheckBoxes(webElements);
    }

    @Step("Click on the button Expand all")
    private void clickExpandAll() {
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector("button[aria-label='Expand all']")));
    }

    @Step("Select the checkbox of the root folder")
    private void clickRootCheckBox() {
        clickJavascriptExecutor(getDriver().findElement(By.cssSelector("[for='tree-node-home'] span.rct-checkbox")));
    }

    @Step("Get prepared expected list")
    private List<String> getExpectedTitles(By selector) {
        List<WebElement> webElementsText = getDriver().findElements(selector);
        return webElementsText.stream()
                .map(el -> el.getText().toLowerCase())
                .toList();
    }

    @Step("Get prepared actual list")
    private List<String> getActualTitles(By selector) {
        return getDriver().findElements(selector).stream()
                .map(el -> el.getText().replaceAll(".doc", "").replaceAll(" ", "").toLowerCase())
                .toList();
    }

    @Step("Check equals expected and actual title lists")
    private void checkEqualsExpAndActTitles(List<String> expectedTitles, List<String> actualTitles) {
        Assertions.assertEquals(expectedTitles, actualTitles);
    }

    @Step("Check size equality: 17")
    private void checkSizeEquality(List<WebElement> webElements) {
        Assertions.assertEquals(17, webElements.size());
    }

    @Step("Check that all checkboxes are selected")
    private void checkSelectedCheckBoxes(List<WebElement> webElements) {
        for (WebElement tmp : webElements) {
            Assertions.assertTrue(tmp.isSelected());
        }
    }
}
