package ui.mainPage;

import org.base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPageTests extends BaseTest {
    private final String path = "https://demoqa.com/";

    @BeforeEach
    void setUp() {
        getDriver().get(path);
    }

    @Test
    void clickBannerImageAndCheckSeleniumTrainingPageOpenedTest() {
        clickJavascriptExecutor(getDriver().findElement(By.className("banner-image")));
        getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[1].toString());
        getWebDriverWait().until(ExpectedConditions.titleIs("Tools QA - Selenium Training"));
    }

    @ParameterizedTest
    @CsvSource({"0, elements", "1, forms", "2, alertsWindows", "3, widgets", "4, interaction", "5, books"})
    void clickButtonsAndCheckRelevantPageOpenedTest(int index, String locator) {
        List<WebElement> webElements = getDriver().findElements(By.cssSelector(".category-cards>div"));
        webElements.get(index).click();
        getWebDriverWait().until(ExpectedConditions.urlContains(path));
        Assertions.assertEquals(path + locator, getDriver().getCurrentUrl());
    }
}
