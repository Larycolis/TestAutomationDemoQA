package ui.mainPage;

import org.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.page.mainPage.MainPage;

public class MainPageTests extends BaseTest {
    private final String path = "https://demoqa.com/";
    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage(getDriver());
        getDriver().get(path);
    }

    @Test
    void clickBannerImageAndCheckSeleniumTrainingPageOpenedTest() {
        mainPage.clickJavascriptExecutor2(getDriver().findElement(By.className("banner-image")));
        mainPage.openNewWindow();
        mainPage.waitTitleIs();
    }

    @ParameterizedTest
    @CsvSource({"0, elements", "1, forms", "2, alertsWindows", "3, widgets", "4, interaction", "5, books"})
    void clickButtonsAndCheckRelevantPageOpenedTest(int index, String locator) {
        mainPage.clickOnTheCard(index);
        mainPage.waitUrlIs(path);
        mainPage.assertActualUrl(path + locator);
    }
}
