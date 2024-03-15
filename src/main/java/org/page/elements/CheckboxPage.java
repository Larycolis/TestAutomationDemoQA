package org.page.elements;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckboxPage {
    private final WebDriver driver;
    @FindBy(css = "button[aria-label='Expand all']")
    private WebElement expendAllBtn;
    @FindBy(css = "[for='tree-node-home'] span.rct-checkbox")
    private WebElement rootCheckbox;
    @FindBy(className = "text-success")
    private List<WebElement> listExpTitles;
    @FindBy(css = "span.rct-title")
    private List<WebElement> listActTitles;
    @FindBy(css = "input[type='checkbox']")
    private List<WebElement> listCheckboxesElement;

    public CheckboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Click on the button Expand all")
    public void clickExpandAll() {
        clickJavascriptExecutor7(expendAllBtn);
    }

    @Step("Select the checkbox of the root folder")
    public void clickRootCheckBox() {
        clickJavascriptExecutor7(rootCheckbox);
    }

    @Step("Check equals expected and actual title lists")
    public void checkEqualsExpAndActTitles() {
        List<String> expectedTitles = getExpectedTitles();
        List<String> actualTitles = getActualTitles();
        Assertions.assertEquals(expectedTitles, actualTitles);
    }

    @Step("Check size equality: 17")
    public void checkSizeEquality() {
        Assertions.assertEquals(17, listCheckboxesElement.size());
    }

    @Step("Check that all checkboxes are selected")
    public void checkSelectedCheckBoxes() {
        for (WebElement tmp : listCheckboxesElement) {
            Assertions.assertTrue(tmp.isSelected());
        }
    }

    private List<String> getExpectedTitles() {
        List<WebElement> webElementsText = listExpTitles;
        return webElementsText.stream()
                .map(el -> el.getText().toLowerCase())
                .toList();
    }

    private List<String> getActualTitles() {
        return listActTitles.stream()
                .map(el -> el.getText().replaceAll(".doc", "").replaceAll(" ", "").toLowerCase())
                .toList();
    }

    private void clickJavascriptExecutor7(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }
}
